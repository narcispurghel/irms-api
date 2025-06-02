package com.irms.api.service;

import com.irms.api.dto.UserDto;
import com.irms.api.dto.request.LoginRequest;
import com.irms.api.dto.request.RegisterRequest;
import com.irms.api.dto.response.LoginResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    UserDto registerUser(RegisterRequest data);

    LoginResponse authenticate(LoginRequest data);

    String logout(HttpServletResponse response);
}
