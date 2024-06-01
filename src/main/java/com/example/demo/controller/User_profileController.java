package com.example.demo.controller;

import com.example.demo.OTD.User_profileOTD;
import com.example.demo.model.User;
import com.example.demo.service.User_profileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/userprofile")
public class User_profileController {
    @Autowired
    User_profileService userProfileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllUsers(@PathVariable int id ) {
        return userProfileService.findAllById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<?> postUser(@RequestBody User_profileOTD user_profileOTD) {
        return userProfileService.postUser(user_profileOTD );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return userProfileService.deleteUser(id);
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<?> putUser(@PathVariable int id, @RequestBody User_profileOTD user_profileOTD) {
        return userProfileService.putUser(id, user_profileOTD);
    }
}
