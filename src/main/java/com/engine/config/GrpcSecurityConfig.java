package com.engine.config;

import com.engine.security.JwtUtil;
import io.grpc.Metadata;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

@Configuration
public class GrpcSecurityConfig {

    private static final Metadata.Key<String> AUTHORIZATION_HEADER =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    private final JwtUtil jwtUtil;

    public GrpcSecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        return (call, headers) -> {
            String authHeader = headers.get(AUTHORIZATION_HEADER);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                try {
                    String subject = jwtUtil.validateAndGetSubject(token);
                    Authentication auth = new UsernamePasswordAuthenticationToken(
                            subject,
                            null,
                            Collections.emptyList()
                    );
                    return auth;
                } catch (Exception e) {
                    return null; // invalid JWT → unauthenticated
                }
            }
            return null; // no header → unauthenticated
        };
    }
}
