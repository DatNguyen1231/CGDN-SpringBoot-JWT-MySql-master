package com.example.demo.controller;

import com.example.demo.model.Dto.ShoppingCartDto;
import com.example.demo.model.entity.ShoppingCartDetail;
import com.example.demo.service.ShoppingCartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/shoppingcartdetail")
@Controller
public class ShoppingCartDetailController {
    @Autowired
    ShoppingCartDetailService shoppingCartDetailService;


    @PostMapping("/add/{id}")

    public ResponseEntity<?> addTypeProduct(@PathVariable long id, @RequestBody ShoppingCartDetail shoppingCartDetail) {
        return shoppingCartDetailService.Add (id,shoppingCartDetail);
    }

}
