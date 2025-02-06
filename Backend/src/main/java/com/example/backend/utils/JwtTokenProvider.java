package com.example.backend.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final JwtUtil jwtUtil;

    public JwtTokenProvider(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(String username, Long userId, Collection<? extends GrantedAuthority> authorities) {
        return jwtUtil.generateToken(username, userId, authorities); 
    }

    public boolean validateToken(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        return jwtUtil.validateToken(token, username, authorities);
    }

    // Ekstrahuje korisničko ime iz tokena
    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }

    // Ekstrahuje uloge iz tokena
    public List<SimpleGrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // Proverava da li je token istekao
    private boolean isTokenExpired(String token) {
        return jwtUtil.getClaimsFromToken(token).getExpiration().before(new Date());
    }
    
    public Long getUserIdFromToken(String token) {
        Claims claims = jwtUtil.getClaimsFromToken(token);
        return claims.get("userId", Long.class); // Čitamo userId iz tokena
    }
    
}
