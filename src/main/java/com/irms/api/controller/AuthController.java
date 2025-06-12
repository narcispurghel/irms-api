package com.irms.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irms.api.dto.UserDto;
import com.irms.api.dto.request.LoginRequest;
import com.irms.api.dto.request.RegisterRequest;
import com.irms.api.dto.response.LoginResponse;
import com.irms.api.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @RequestBody RegisterRequest requestBody) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(APPLICATION_JSON)
                .body(authService.registerUser(requestBody));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest requestBody) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(authService.authenticate(requestBody));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        return ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(authService.logout(response));
    }
}
