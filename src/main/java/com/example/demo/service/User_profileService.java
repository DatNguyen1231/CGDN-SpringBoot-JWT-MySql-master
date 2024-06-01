package com.example.demo.service;

import com.example.demo.OTD.User_profileOTD;
import com.example.demo.model.User;
import com.example.demo.model.User_profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface User_profileService {

    ResponseEntity<?> findAllById(int id);

    ResponseEntity<?> postUser(User_profileOTD userProfileOTD);

    ResponseEntity<?>  deleteUser(int id);

    ResponseEntity<?>  putUser(int id,User_profileOTD newUser);

}
