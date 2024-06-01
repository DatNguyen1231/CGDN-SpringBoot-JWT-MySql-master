package com.example.demo.service.serviceIplm;

import com.example.demo.OTD.User_profileOTD;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.User_profile;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.User_profileRepository;
import com.example.demo.service.User_profileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class User_profileServiceImpl implements User_profileService {
    @Autowired
    User_profileRepository user_profileRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<?> findAllById(int id) {

        User_profile userProfile = user_profileRepository.findByUserId(id).orElse(null);
        if (userProfile != null) {
            // Return ResponseEntity with the found user profile and HTTP status OK
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } else {
            // Return ResponseEntity with a message indicating that the user profile was not found
            return new ResponseEntity<>("User profile not found", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<?> postUser(User_profileOTD userProfileOTD) {

        User_profile user_profile = new User_profile();

        user_profile.setName(userProfileOTD.getName());
        user_profile.setSex(userProfileOTD.getSex());
        user_profile.setBirth((user_profile.getBirth()));
        user_profile.setAddress(userProfileOTD.getAddress());
        user_profile.setPhoneNumber(userProfileOTD.getPhoneNumber());
        //lấy thông tin
        User user =userRepository.findById (userProfileOTD.getUserID()).orElse(null);
        if (user != null ) {
            user_profile.setUser(user);

            return new ResponseEntity<>(user_profileRepository.save(user_profile), HttpStatus.OK);
        }
        else {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> putUser(int id, User_profileOTD newUser) {
        Optional<User_profile> user_profile = user_profileRepository.findById(id);

        return user_profile.map(userToUpdate -> {
            // Update user information
            userToUpdate.setName(newUser.getName());
            userToUpdate.setPhoneNumber(newUser.getPhoneNumber());
            userToUpdate.setAddress(newUser.getAddress());
            userToUpdate.setBirth(newUser.getBirth());
            userToUpdate.setSex(newUser.getSex());

            // Save the updated user profile
            user_profileRepository.save(userToUpdate);

            // Return a successful response
            return ResponseEntity.ok("User profile updated successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }




//    @Override
//    public ResponseEntity<?> deleteUser(int id) {
//        return new ResponseEntity<>( user_profileRepository.deleteById(id), HttpStatus.OK);
//    }

//    @Override
//    public ResponseEntity<?> putUser(int id, User_profile newUser) {
//        Optional<User_profile> existingUser = user_profileRepository.findById(id);
//        return  return existingUser.map(userToUpdate -> {
//            // Cập nhật thông tin người dùng
//            userToUpdate.setEmail(user.getEmail());
//            userToUpdate.setRole(user.getRole());
//            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//    }
}
