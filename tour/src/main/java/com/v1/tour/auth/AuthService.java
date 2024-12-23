package com.v1.tour.auth;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.v1.tour.auth.dto.LoginDto;
import com.v1.tour.auth.userdetails.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        var token = jwtService.generateJwtToken((UserDetailsImpl) authentication.getPrincipal());
        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(UUID.randomUUID())
                .build();
    }
}
