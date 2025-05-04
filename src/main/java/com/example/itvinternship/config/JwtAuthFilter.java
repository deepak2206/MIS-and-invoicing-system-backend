package com.example.itvinternship.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.itvinternship.model.User;
import com.example.itvinternship.repo.UserRepository;
import com.example.itvinternship.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        log.debug("🔍 Checking path in JwtAuthFilter: {}", path);
        return path.startsWith("/api/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            log.debug("🏗️ Processing request: {} {}", request.getMethod(), request.getRequestURI());
            
            final String authHeader = request.getHeader("Authorization");
            String token = null;
            String email = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                log.debug("🔑 Token found in header");
                
                try {
                    email = jwtService.extractUsername(token);
                    log.debug("📧 Extracted email from token: {}", email);
                } catch (Exception e) {
                    log.error("❌ Token validation failed", e);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                    return;
                }
            } else {
                log.debug("⚠️ No Bearer token found in Authorization header");
            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.debug("🔎 Looking up user with email: {}", email);
                User user = userRepository.findByEmail(email)
                        .orElse(null);

                if (user != null) {
                    log.debug("👤 User found: {}", user.getEmail());
                    if (jwtService.isTokenValid(token, user.getEmail())) {
                        log.debug("✅ Token is valid for user: {}", user.getEmail());
                        
                        UsernamePasswordAuthenticationToken authToken = 
                            new UsernamePasswordAuthenticationToken(
                                user.getEmail(), 
                                null, 
                                new ArrayList<>());
                        
                        authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                        
                        SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                        
                        log.debug("🔒 Authentication set in SecurityContext");
                    } else {
                        log.warn("⚠️ Token validation failed for user: {}", user.getEmail());
                    }
                } else {
                    log.warn("⚠️ No user found with email: {}", email);
                }
            }

            filterChain.doFilter(request, response);
            
        } catch (Exception e) {
            log.error("🔥 Unexpected error in JwtAuthFilter", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                             "Authentication processing failed");
        }
    }
}