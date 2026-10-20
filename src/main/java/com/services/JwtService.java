package com.services;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expiry;

    public JwtService(@Value("${app.jwt.secret}") String secret, @Value ("${app.jwt.expiration-ms}") long expString) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiry = expString;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(authentication.getName())
                .claim("role", "ROLE_ADMIN")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expiry)))
                .signWith(key)
                .compact();
    }

    public long expiryInSeconds(){
        return (expiry/1000);
    }

    public Claims parse(String token){
        return Jwts.parser().verifyWith(this.key).build().parseSignedClaims(token).getPayload();
    }
}
