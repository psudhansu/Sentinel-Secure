package com.example.Security_JWT.service;

import com.example.Security_JWT.dto.LoginRequest;
import com.example.Security_JWT.dto.RefreshTokenRequest;
import com.example.Security_JWT.dto.RegisterRequest;
import com.example.Security_JWT.dto.TokenPair;
import com.example.Security_JWT.entity.Users;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthService {

    Users register(RegisterRequest rr);
    List<Users> viewAllUsers();
    TokenPair login(LoginRequest loginrequest);

    TokenPair refreshToken(RefreshTokenRequest request);
}
