package com.irms.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irms.api.dto.entities.UserDto;
import com.irms.api.dto.request.LoginRequest;
import com.irms.api.dto.request.RegisterRequest;
import com.irms.api.dto.response.LoginResponse;
import com.irms.api.entity.Authority;
import com.irms.api.entity.User;
import com.irms.api.exception.ApiExceptionFactory;
import com.irms.api.repository.UserRepository;
import com.irms.api.service.AuthService;
import com.irms.api.service.JwtService;
import com.irms.api.util.ModelConverter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto registerUser(RegisterRequest data) {
        var authority = new Authority(data.role());
        var user = new User();
        user.setUsername(data.username());
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.getAuthorities().add(authority);
        user = userRepository.save(user);
        LOGGER.info("User created: {}", user);
        return ModelConverter.toUserDto(user);
    }

    @Override
    public LoginResponse authenticate(LoginRequest data) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                data.email(),
                data.password());
        Authentication fullAuth;
        try {
            fullAuth = authenticationManager.authenticate(authToken);
        } catch (AuthenticationException ex) {
            throw ApiExceptionFactory.unauthorized(ex.getMessage(), ex);
        }
        User user = (User) fullAuth.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new LoginResponse(user.getUsername(), roles, jwtToken);
    }

    @Override
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "Logout success!";
    }
}
