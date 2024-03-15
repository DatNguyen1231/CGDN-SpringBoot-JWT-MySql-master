package com.example.demo.service;

import com.example.demo.entity.DescribeProduct;
import com.example.demo.entity.Product;
import org.springframework.http.ResponseEntity;

public interface DescribeProductSevice {
    ResponseEntity<?> add(DescribeProduct describeProduct);
    ResponseEntity<?> delete(long id);

    ResponseEntity<?> get();
}
