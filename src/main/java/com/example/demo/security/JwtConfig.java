package com.example.demo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {


    private String secretKey="your-long-secure-secret-key";

    // Bean để cung cấp SecretKey cho JwtTokenProvider


    // Bean để cung cấp PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecretKey secretKey() {
        // Sử dụng phương thức secretKeyFor để tạo khóa an toàn
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}