package com.example.Security_JWT.controller;

import com.example.Security_JWT.dto.LoginRequest;
import com.example.Security_JWT.dto.RefreshTokenRequest;
import com.example.Security_JWT.dto.RegisterRequest;
import com.example.Security_JWT.dto.TokenPair;
import com.example.Security_JWT.entity.Users;
import com.example.Security_JWT.service.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/user")
    private ResponseEntity<Users> RegisterUser(@Valid @RequestBody RegisterRequest rr){

        Users getUser = authService.register(rr);

        return new ResponseEntity<>(getUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<List<Users>> ViewAllUser(){
        List<Users> allusers = authService.viewAllUsers();
        return new ResponseEntity<>(allusers, HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<?> Login(@Valid @RequestBody LoginRequest login){
        TokenPair tokenPair = authService.login(login);
        return ResponseEntity.ok(tokenPair);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        TokenPair tokenPair = authService.refreshToken(request);
        return ResponseEntity.ok(tokenPair);
    }
}
