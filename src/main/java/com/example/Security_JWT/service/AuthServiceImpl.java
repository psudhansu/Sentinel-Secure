package com.example.Security_JWT.service;

import com.example.Security_JWT.dto.LoginRequest;
import com.example.Security_JWT.dto.RefreshTokenRequest;
import com.example.Security_JWT.dto.RegisterRequest;
import com.example.Security_JWT.dto.TokenPair;
import com.example.Security_JWT.entity.Users;
import com.example.Security_JWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailService userDetailService;


    @Override
    public Users register(RegisterRequest rr) {
        if(userRepository.existsByEmail(rr.getEmail())){
            throw new IllegalArgumentException("User already Exist with Username" + rr.getEmail());
        }

        Users user = new Users();
                user.setName(rr.getName());
                user.setEmail(rr.getEmail());
                user.setPassword(passwordEncoder.encode(rr.getPassword()));
                user.setRole(rr.getRole());

        return userRepository.save(user);
    }

    @Override
    public List<Users> viewAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public TokenPair login(LoginRequest loginrequest){
    //Authenticate the User
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginrequest.getUsername(),
                        loginrequest.getPassword()
                )
        );
        //Set authentication in Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Generate token pair
        TokenPair tokenPair = jwtService.generateTokenPair(authentication);
        return tokenPair;
    }


    @Override
    public TokenPair refreshToken(RefreshTokenRequest request) {

        String refreshToken = request.getRefreshToken();
        // check if it is valid refresh token
        if(!jwtService.isRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String user = jwtService.extractUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailService.loadUserByUsername(user);

        if (userDetails == null) {
            throw new IllegalArgumentException("User not found");
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        String accessToken = jwtService.generateAccessToken(authentication);
        return new TokenPair(accessToken, refreshToken);
    }
}
