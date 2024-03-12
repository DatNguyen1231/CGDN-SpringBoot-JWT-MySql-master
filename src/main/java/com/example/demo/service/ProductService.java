package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> add(Product productMoto);
    ResponseEntity<?> get();

    ResponseEntity<?> delete(long id);
}
