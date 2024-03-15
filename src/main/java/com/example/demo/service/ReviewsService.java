package com.example.demo.service;


import com.example.demo.entity.Reviews;
import org.springframework.http.ResponseEntity;

public interface ReviewsService {
    ResponseEntity<?> add(Reviews reviews);
    ResponseEntity<?> get();

    ResponseEntity<?> delete(long id);
}
