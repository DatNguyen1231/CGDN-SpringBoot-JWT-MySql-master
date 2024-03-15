package com.example.demo.controller;

import com.example.demo.model.Dto.ShoppingCartDto;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/shoppingcart")
@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;


    @PostMapping("/add")

    public ResponseEntity<?> addTypeProduct(@RequestBody ShoppingCartDto shoppingCartDto) {
        return shoppingCartService.AddCart(shoppingCartDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return shoppingCartService.getAll();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return shoppingCartService.deltete(id);
    }


}
