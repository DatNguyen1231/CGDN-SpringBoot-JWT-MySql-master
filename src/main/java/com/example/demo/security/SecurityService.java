package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    AuthenticationManager authenticationManager;
   JwtTokenProvider jwtTokenProvider;

    // Constructor nhận các dependency cần thiết


    // Phương thức xác thực và sinh token
    public String authenticateAndGenerateToken(String username, String password) {
        // Xác thực thông tin đăng nhập sử dụng AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Lưu thông tin xác thực vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Sinh token từ thông tin xác thực
        return jwtTokenProvider.generateToken(authentication);
    }
}
