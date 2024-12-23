package com.v1.tour.auth;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.v1.tour.auth.dto.LoginDto;
import com.v1.tour.auth.dto.RefreshTokenDto;
import com.v1.tour.auth.userdetails.UserDetailsImpl;
import com.v1.tour.exception.CustomException;
import com.v1.tour.role.RoleService;
import com.v1.tour.user.UserService;
import com.v1.tour.usertoken.UserTokenService;
import com.v1.tour.usertoken.dto.UserTokenDto;
import com.v1.tour.utils.Constants.ErrorType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserTokenService userTokenService;
    private final UserService userService;
    private final RoleService roleService;

    public AuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        var user = (UserDetailsImpl) authentication.getPrincipal();
        var accessToken = jwtService.generateJwtToken(user);

        var userTokenDto = UserTokenDto.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(UUID.randomUUID())
                .build();
        userTokenService.create(userTokenDto);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(userTokenDto.getRefreshToken())
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        var userToken = userTokenService.findByRefreshToken(refreshTokenDto.getRefreshToken());

        if (Boolean.TRUE.equals(userToken.getIsRefreshed()) || userToken.getRefreshTokenExpire().before(new Date()))
            throw new CustomException(ErrorType.REFRESH_TOKEN_EXPIRED);

        var user = userService.findById(userToken.getUserId());
        var roles = roleService.findAllByUserId(user.getId());

        var userdetailsImpl = UserDetailsImpl.build(user, roles);
        var accessToken = jwtService.generateJwtToken(userdetailsImpl);

        var userTokenDto = UserTokenDto.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(UUID.randomUUID())
                .build();
        userTokenService.create(userTokenDto);

        userTokenService.updateIsRefreshed(userToken.getId(), true);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(userTokenDto.getRefreshToken())
                .build();
    }
}
