package com.example.demo.security;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {


    private Long expiration=12312L;

    SecretKey secretKey;

    // Constructor nhận SecretKey từ JwtConfig


    // Phương thức sinh token từ thông tin xác thực
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 360000);

        // Sử dụng thư viện jjwt để tạo token
        return Jwts.builder()
                .setSubject(authentication.getName()) // Tên người dùng làm subject của token
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS512) // Sử dụng thuật toán HS512 để ký
                .compact();
    }

    // Bạn có thể thêm các phương thức khác để kiểm tra tính hợp lệ, trích xuất thông tin, v.v.
}
