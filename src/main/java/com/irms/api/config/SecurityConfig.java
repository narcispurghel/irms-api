package com.irms.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;

import com.irms.api.security.CustomAccessDeniedHandler;
import com.irms.api.security.JwtAuthEntryPoint;
import com.irms.api.security.JwtFilter;
import com.irms.api.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] PUBLIC_API_ENDPOINTS = {
            "/test",
            "/static/**",
            "/",
            "/favicon.ico",
            "/api/v1/auth/register",
            "/api/v1/auth/login",
    };
    private static final String[] SWAGGER_ENDPOINTS = {
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/webjars/**",
            "/swagger-ui/favicon-16x16.png",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-initializer.js",
            "/v3/api-docs/swagger-config"
    };

    private final UserService uerService;
    private final JwtFilter jwtFilter;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
            UserService uerService,
            JwtFilter jwtFilter,
            JwtAuthEntryPoint jwtAuthEntryPoint,
            CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.uerService = uerService;
        this.jwtFilter = jwtFilter;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(getPublicEndpoints()).permitAll();
                    request.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authenticationManager(authenticationManager())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(jwtAuthEntryPoint);
                    e.accessDeniedHandler(customAccessDeniedHandler);
                })
                .addFilterAfter(jwtFilter, SecurityContextHolderFilter.class)
                .build();
    }

    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() {
        final var provider = new DaoAuthenticationProvider(uerService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    public static String[] getPublicEndpoints() {
        List<String> publicEndpointsTemp = new ArrayList<>();
        publicEndpointsTemp.addAll(List.of(SWAGGER_ENDPOINTS));
        publicEndpointsTemp.addAll(List.of(PUBLIC_API_ENDPOINTS));
        String[] publicEndpoints = new String[publicEndpointsTemp.size()];
        for (int i = 0; i < publicEndpointsTemp.size(); ++i) {
            publicEndpoints[i] = publicEndpointsTemp.get(i);
        }
        return publicEndpoints;
    }
}
