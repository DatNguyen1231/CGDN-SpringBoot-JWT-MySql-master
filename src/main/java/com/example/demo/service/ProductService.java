package com.example.demo.service;

import com.example.demo.Dto.ProductDto;
import com.example.demo.entity.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> add(ProductDto dto);
    ResponseEntity<?> get();

    ResponseEntity<?> delete(long id);
}
