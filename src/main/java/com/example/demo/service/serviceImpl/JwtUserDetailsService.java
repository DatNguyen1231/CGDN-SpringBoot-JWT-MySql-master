package com.example.demo.service.serviceImpl;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.Dto.*;
import com.example.demo.model.entity.DAOUser;
import com.example.demo.model.entity.Role;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;


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


@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private Messenger messenger;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

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

    public UserDTO getUserDto(String username){

        return new UserDTO(userRepository.findByUsername(username));
    }

    //check thông tin người dùng có để trống không
    private boolean isValidUser(UserRequestDto userRequestDto) {
        return userRequestDto.getUsername().trim().isEmpty()
                || userRequestDto.getPassword().trim().isEmpty()
                || userRequestDto.getAddress().trim().isEmpty()
                || userRequestDto.getPhoneNumber().trim().isEmpty()
                || userRequestDto.getEmail().trim().isEmpty()
                || userRequestDto.getFullName().trim().isEmpty();
    }

    // check thông tin không được để trống

    // check tk mk phải dài hơn 5kis tự
    private boolean isValidUserPassword(UserRequestDto userRequestDto) {
        return userRequestDto.getUsername().length() < 5
                || userRequestDto.getPassword().length() < 5;
    }

    private boolean isValidGmail( UserRequestDto userRequestDto) {
        String regex = "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@gmail.com$";
        return Pattern.matches(regex, userRequestDto.getEmail());
    }
    //dang ki
    public ResponseEntity<?> save(UserRequestDto userRequestDto) {

        try {
            // check thông tin trước khi đăng kí tk
            if (isValidUser(userRequestDto)) {
                messenger.setMessenger("Vui lòng nhập đầy đủ thông tin");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }

            if (isValidUserPassword(userRequestDto)) {
                messenger.setMessenger("Tài khoản hoặc mật khẩu phải dài hơn 5 kí tự");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }
            if(!isValidGmail(userRequestDto)){
                messenger.setMessenger("Gmail không hợp lệ");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }
            if (userRepository.findByUsername(userRequestDto.getUsername()) != null) {
                messenger.setMessenger("Username đã tồn tại");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }

            //tạo 1 User để luu vào csdl
            DAOUser daoUser = new DAOUser();
            // gán thông tin cho user lấy từ dto
            daoUser.setUsername(userRequestDto.getUsername());
            daoUser.setPassword(bcryptEncoder.encode(userRequestDto.getPassword()));
            daoUser.setAddress(userRequestDto.getAddress());
            daoUser.setEmail(userRequestDto.getEmail());
            daoUser.setFullName(userRequestDto.getFullName());
            daoUser.setPhoneNumber(userRequestDto.getPhoneNumber());

            //2 mac dinh laf user
            Role role = roleRepository.findById(2L).orElse(null);
            daoUser.setRole(role);
            //lưu
            userRepository.save(daoUser);
            log.info("Tạo tài khoản thành công");
            messenger.setMessenger("Tạo tài khoản thành công");
            return new ResponseEntity<>(messenger, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred ");
            messenger.setMessenger("error cmnr ))");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }


    }
    // hàm check tk mk
    private ResponseEntity<?> validateLoginInfo(JwtRequest authenticationRequest){
        if (authenticationRequest.getUsername().trim().isEmpty()
                && authenticationRequest.getPassword().trim().isEmpty()) {
            messenger.setMessenger("Vui lòng nhập tài khoản và mật khẩu");
            return new ResponseEntity<>(messenger, HttpStatus.UNAUTHORIZED);
        }
        if (authenticationRequest.getUsername().trim().isEmpty()
        ) {
            messenger.setMessenger("Vui lòng nhập tài khoản");
            return new ResponseEntity<>(messenger, HttpStatus.UNAUTHORIZED);
        }
        if ( authenticationRequest.getPassword().trim().isEmpty()
        ) {
            messenger.setMessenger("Vui lòng nhập mật khẩu");
            return new ResponseEntity<>(messenger, HttpStatus.UNAUTHORIZED);
        }
        return null;
    }


    //login
    public ResponseEntity<?> login(JwtRequest authenticationRequest) throws Exception{
        try {
            //check username password có hợp lệ không
            ResponseEntity<?> validationResponse = validateLoginInfo(authenticationRequest);
            if (validationResponse != null) {
                return validationResponse;
            }
            //xác thực thông tin đăng nhập
            authenticate(authenticationRequest.getUsername().trim(), authenticationRequest.getPassword());

            //tạo 1 userDetails
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            // tạo token
            final String token = jwtTokenUtil.generateToken(userDetails, userDetailsService.getUserDto(authenticationRequest.getUsername()));

            //tra về token
            log.info(authenticationRequest.getUsername() + " đăng nhập");
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            messenger.setMessenger("Sai tài khoản hoặc mật khẩu");
            return new ResponseEntity<>(messenger, HttpStatus.UNAUTHORIZED);
        }
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
