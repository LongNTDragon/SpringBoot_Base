package com.v1.tour.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.v1.tour.auth.userdetails.UserDetailsServiceImpl;
import com.v1.tour.utils.Constants.ErrorType;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthFilterInternal extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = this.parseJwt(request);
            if (jwt != null && jwtService.validateToken(jwt)) {
                var subject = jwtService.getSubjectFromJwtToken(jwt);

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                var userDetailsImpl = userDetailsServiceImpl.loadUserByUsername(subject);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetailsImpl,
                        null,
                        userDetailsImpl.getAuthorities());
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            request.setAttribute(ErrorType.INVALID_JWT, ErrorType.EXPIRED_JWT);
            throw ex;
        } catch (Exception ex) {
            request.setAttribute(ErrorType.INVALID_JWT, ErrorType.INVALID_JWT);
            throw ex;
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
