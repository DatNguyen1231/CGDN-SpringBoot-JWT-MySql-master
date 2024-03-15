package com.example.demo.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.DAOUser;


import com.example.demo.Dto.Messenger;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.
The Spring Security Authentication Manager calls this method for getting the user details from the database
when authenticating the user details provided by the user. Here we are getting the user details from a hardcoded
User List. In the next tutorial we will be adding the DAO implementation for fetching User Details from the Database.
Also the password for a user is stored in encrypted format using BCrypt.
Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private Messenger messenger;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Create a list to store user's authorities (roles)
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Add roles to authorities
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()));
        // You can add more roles as needed

        // Create UserDetails object with roles
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public String getRole(String username){

        return  userRepository.findByUsername(username).getRole().getRole();
    }

    public ResponseEntity<?>  save(DAOUser user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            messenger.setMessenger("Username đã tồn tại");
            return  new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(user);
        messenger.setMessenger("Tạo tài khoản thành công");
        return  new ResponseEntity<>(messenger, HttpStatus.OK);
    }
}
