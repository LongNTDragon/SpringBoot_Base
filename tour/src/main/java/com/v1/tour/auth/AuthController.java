package com.v1.tour.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.tour.auth.dto.LoginDto;
import com.v1.tour.auth.userdetails.UserDetailsImpl;
import com.v1.tour.base.BaseController;
import com.v1.tour.base.ResponseObject;
import com.v1.tour.utils.Constants.UrlPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.AUTH)
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        return this.onSuccess(jwtService.generateJwtToken((UserDetailsImpl) authentication.getPrincipal()));
    }
}
