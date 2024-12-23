package com.v1.tour.auth;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.v1.tour.auth.userdetails.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.jwt.expiration-time.access-token}")
    private Long tokenExpiration;

    public String generateJwtToken(UserDetailsImpl userDetailsImpl) {
        return Jwts.builder()
                .setSubject(userDetailsImpl.getEmail())
                .claim("username", userDetailsImpl.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + this.tokenExpiration))
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getSubjectFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token);
        return true;
    }
}
