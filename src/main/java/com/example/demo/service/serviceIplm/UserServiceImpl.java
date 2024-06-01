package com.example.demo.service.serviceIplm;

import com.example.demo.OTD.UserOTD;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    Map<String, String> response = new HashMap<>();



    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> findAllById(User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            UserOTD userOTD = new UserOTD();
            User existingUser = userOptional.get();
            userOTD.setId(existingUser.getId());
            userOTD.setEmail(existingUser.getEmail());
            userOTD.setRole(existingUser.getRole());
            return ResponseEntity.ok(userOTD);
        }



        return new ResponseEntity<>("sai mật khẩu",HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<User> postUser(User newUser) {
        if (newUser == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userOptional = userRepository.findByEmail(newUser.getEmail());

        // Kiểm tra xem tài khoản đã tồn tại hay chưa
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Tài khoản đã tồn tại
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> putUser(int id, User user) {
        Optional<User> existingUser = userRepository.findById(id);

        return existingUser.map(userToUpdate -> {
            // Cập nhật thông tin người dùng
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setRole(user.getRole());
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

            // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
            userRepository.save(userToUpdate);
            return ResponseEntity.ok(userToUpdate);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}