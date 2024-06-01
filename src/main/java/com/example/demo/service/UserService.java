package com.example.demo.service;

import com.example.demo.OTD.UserOTD;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    ResponseEntity<?> findAllById(User newUser);

    ResponseEntity<?> postUser(User newUser);

    ResponseEntity<?>  deleteUser(int id);

    ResponseEntity<?>  putUser(int id, User user);
}

