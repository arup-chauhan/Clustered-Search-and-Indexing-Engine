package com.engine.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long ttlMillis;

    public JwtUtil(
            @Value("${security.jwt.hmacSecret}") String base64Secret,
            @Value("${security.jwt.ttlSeconds}") long ttlSeconds
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret));
        this.ttlMillis = ttlSeconds * 1000;
    }

    // Generate JWT with subject (username) and optional roles
    public String generateToken(String subject, Set<String> roles) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ttlMillis))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Default overload → assign USER role
    public String generateToken(String subject) {
        return generateToken(subject, Set.of("USER"));
    }

    // Validate token and return subject (username)
    public String validateAndGetSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
