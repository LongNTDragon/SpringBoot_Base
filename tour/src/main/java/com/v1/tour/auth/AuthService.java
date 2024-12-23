package com.v1.tour.auth;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.v1.tour.auth.dto.LoginDto;
import com.v1.tour.auth.userdetails.UserDetailsImpl;
import com.v1.tour.usertoken.UserTokenService;
import com.v1.tour.usertoken.dto.UserTokenDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserTokenService userTokenService;

    public AuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        var user = (UserDetailsImpl) authentication.getPrincipal();
        var accessToken = jwtService.generateJwtToken(user);
        var refreshToken = UUID.randomUUID();

        var userTokenDto = UserTokenDto.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        userTokenService.create(userTokenDto);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
