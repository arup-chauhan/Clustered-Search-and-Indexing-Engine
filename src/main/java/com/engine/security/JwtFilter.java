package com.engine.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();

        System.out.println("JwtFilter servletPath = " + servletPath);
        System.out.println("JwtFilter requestURI  = " + uri);
        System.out.println("JwtFilter contextPath = " + ctx);

        // Skip JWT validation for auth and actuator endpoints
        if (uri.startsWith("/api/auth/") || uri.startsWith("/actuator/")) {
            System.out.println("Skipping JWT check for: " + uri);
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String subject = jwtUtil.validateAndGetSubject(token);
                System.out.println("Valid token for subject: " + subject);
                var auth = new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                System.out.println("Invalid token: " + e.getMessage() + " for URI: " + uri);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else {
            System.out.println("Missing Authorization header on: " + uri);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}
