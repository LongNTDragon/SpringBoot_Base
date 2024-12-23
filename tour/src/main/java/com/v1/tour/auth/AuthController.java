package com.v1.tour.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.tour.auth.dto.LoginDto;
import com.v1.tour.auth.dto.RefreshTokenDto;
import com.v1.tour.base.BaseController;
import com.v1.tour.base.ResponseObject;
import com.v1.tour.utils.Constants.UrlPath;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.AUTH)
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService service;

    @PostMapping("login")
    public ResponseEntity<ResponseObject> login(@Valid @RequestBody LoginDto loginDto) {
        return this.onSuccess(service.login(loginDto));
    }

    @PostMapping("refresh_token")
    public ResponseEntity<ResponseObject> refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return this.onSuccess(service.refreshToken(refreshTokenDto));
    }
}
