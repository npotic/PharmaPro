package com.example.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.backend.filters.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                		
                		 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                		 .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()    
                         .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() 
                         .requestMatchers(HttpMethod.GET, "/api/lekovi").permitAll()

                        .requestMatchers("/api/auth/**").permitAll() 
                        .requestMatchers("/api/lekovi/lista").permitAll() 
                        

                        .requestMatchers("/images/**", "/upload/**", "/profile-picture/**", "/request/**").permitAll()

                        .requestMatchers("/api/reports/generate").hasRole("ADMIN")

                        .requestMatchers("/api/lekovi/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN") 
                        .requestMatchers("/api/messages/**").hasAnyRole("USER", "ADMIN") 
                        .requestMatchers("/api/friends/**").hasAnyRole("USER", "ADMIN") 

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}