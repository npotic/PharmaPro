package com.example.backend.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000L; 

    public String generateToken(String username, Long userId, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        claims.put("userId", userId); 

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public boolean validateToken(String token, String username, Collection<? extends GrantedAuthority> authorities) {
        Claims claims = getClaimsFromToken(token);
        String tokenUsername = claims.getSubject();
        List<String> tokenRoles = claims.get("roles", List.class);

        List<String> authorityRoles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return username.equals(tokenUsername) && tokenRoles.equals(authorityRoles) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }
}
