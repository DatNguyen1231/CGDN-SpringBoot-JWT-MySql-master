package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    SecurityService securityService;


    // Constructor nhận dependency cần thiết
    @Autowired
    AuthenticationManager  SauthenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    // Endpoint xử lý request đăng nhập
    @PostMapping("")
    public String login(@RequestBody User loginRequest) {
        String username ="";
        String password ="1234";
        SauthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      //  String token = jwtTokenProvider.generateToken(User aa);

        // Nếu không có ngoại lệ, xác thực thành công
        System.out.println("Authentication successful for user: " + username);

        return "Bearer " +"ok";
    }

    // Bạn có thể thêm các endpoint khác liên quan đến xác thực tại đây
}