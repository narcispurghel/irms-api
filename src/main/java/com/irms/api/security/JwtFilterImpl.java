package com.irms.api.security;

import static com.irms.api.constants.EndpointsConstants.PUBLIC_API_ENDPOINTS;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.irms.api.entity.User;
import com.irms.api.repository.UserRepository;
import com.irms.api.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterImpl extends JwtFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilterImpl.class);

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtFilterImpl(JwtService jwtService,
            UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Processing request: {}", request.getRequestURI());
        String path = request.getRequestURI();
        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("Getting authentication from SecurityContext");
        if (authentication != null) {
            LOGGER.info("Authentication is non-null, pass the filter");
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        LOGGER.info("Getting authorization header from request");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            LOGGER.info("Invalid authorization header: {}, throwing JWTAuthenticationException", authHeader);
            response.sendError(401, "Missing or invalid authorization header");
            return;
        }
        String jwtToken = authHeader.substring(7);
        if (!jwtService.isTokenValid(jwtToken)) {
            response.sendError(401, "Invalid jwt token");
            return;
        }
        String subject = jwtService.extractUsername(jwtToken);
        if (subject == null) {
            response.sendError(401, "Invalid jwt token");
            return;
        }
        User user = userRepository.findByUsername(subject)
                .orElse(null);
        if (user == null) {
            response.sendError(401, "User is not signed up!");
            return;
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean isPublicPath(String path) {
        String publicPath;
        if (path.equals("/")) {
            publicPath = path;
        } else {
            publicPath = PUBLIC_API_ENDPOINTS.stream()
                    .filter(e -> {
                        if (e.endsWith("/**")) {
                            String prefix = e.substring(0, e.length() - 3);
                            return path.startsWith(prefix);
                        } else {
                            return e.equals(path);
                        }
                    })
                    .collect(Collectors.joining());
        }
        return !publicPath.isEmpty();
    }
}