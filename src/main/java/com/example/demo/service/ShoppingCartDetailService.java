package com.example.demo.service;

import com.example.demo.model.Dto.ShoppingCartDto;
import com.example.demo.model.entity.ShoppingCart;
import com.example.demo.model.entity.ShoppingCartDetail;
import org.springframework.http.ResponseEntity;

public interface ShoppingCartDetailService {
    ResponseEntity<?> Add(Long shoppingCartId, ShoppingCartDetail shoppingCartDetail);
    ResponseEntity<?> getAll();
    ResponseEntity<?> deltete(long id);
}
