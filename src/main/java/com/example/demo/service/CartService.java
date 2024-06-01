package com.example.demo.service;

import com.example.demo.OTD.CartOTD;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public ResponseEntity<?> upload(CartOTD cart);
    public        ResponseEntity<?>delete(int id);
    public       ResponseEntity<?>put(int id, CartOTD cartOTD);
    ResponseEntity<List<?>> findByUserIdAndStatus(int userId,int status);
   ResponseEntity<List<?>> findByStatus(int status);
    ResponseEntity<List<?>> findByUserId(int UserId);
   ResponseEntity<List<?>> getAllCart();
}
