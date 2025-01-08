package com.v1.leadservice.auth;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.v1.leadservice.auth.userdetails.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.jwt.expiration-time.access-token}")
    private Long tokenExpiration;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getSubjectFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    @SuppressWarnings("unchecked")
    public UserDetailsImpl parseJwtTokenToUserDetailsImpl(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build()
                .parseClaimsJws(token).getBody();

        List<String> roles = claims.get("roles", List.class);
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return UserDetailsImpl.builder()
                .id(claims.get("id", String.class))
                .email(claims.getSubject())
                .username(claims.get("username", String.class))
                .authorities(authorities)
                .build();
    }

    public Boolean validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token);
        return true;
    }
}
