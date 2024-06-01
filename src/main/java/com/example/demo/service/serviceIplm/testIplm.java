package com.example.demo.service.serviceIplm;

import com.example.demo.controller.TestController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class testIplm implements testService{

    @Autowired
    UserRepository userRepository;

    @Override
    public String hihi() {


        Optional<User> userOptional=  userRepository. findByEmail("1@1.11");
        if (userOptional.isPresent()) {
            // Người dùng không tồn tại, bạn có thể lấy đối tượng User từ Optional

            String Pass= userOptional.get().getPassword();
            Boolean pass1= new BCryptPasswordEncoder().matches("11",Pass);
            if( pass1){
                User userOTD = userOptional.get();
                return "có";
            }
        }


        return "không";
    }


    @Override
    public List<User> findAllById(int id) {
        List<Integer>list = new ArrayList<>();
        list.add(id);
        return userRepository.findAllById(list);
    }
}
