package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()

                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(loginRequest.getEmail());
       // System.out.println(token);
        return ResponseEntity.ok("hkhk"+token);
    }
    @PostMapping("/login")
    public ResponseEntity<?> getAllUsers(@RequestBody User newUser) {
        return userService.findAllById(newUser);
    }
    @PostMapping("/add")
    public ResponseEntity<?> postUser(@RequestBody User newUser) {
        return userService.postUser(newUser);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return userService.deleteUser(id);
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody User user) {
        return userService.putUser(id, user);
    }


}
