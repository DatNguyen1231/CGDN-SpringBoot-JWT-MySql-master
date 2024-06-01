package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface testService {

   String hihi();
   List<User> findAllById(int id);
}
