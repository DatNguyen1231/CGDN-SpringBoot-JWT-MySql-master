package com.example.demo.controller;

import com.example.demo.OTD.CartOTD;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<?> postUser(@RequestBody CartOTD cartOTD) {

        return cartService.upload((cartOTD));

    }
    @GetMapping("/get/all")
    public ResponseEntity<?> get1() {
        return cartService.getAllCart();
    }
    @GetMapping("/get/user/{id}")
    public ResponseEntity<?> getUserID(@PathVariable int id ) {
        return cartService.findByUserId(id);
    }
    @GetMapping("/get/{id}/{status}")
    public ResponseEntity<?> get(@PathVariable int id, @PathVariable int status ) {
        return cartService.findByUserIdAndStatus(id, status);
    }


    @GetMapping("/get/status/{status}")
    public ResponseEntity<?> getStatus( @PathVariable int status ) {
        return cartService.findByStatus(status);
    }
    @PutMapping("put/{id}")
    public ResponseEntity<?>put(@PathVariable int id,@RequestBody CartOTD cartOTD){

        return cartService.put(id,cartOTD);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>delete(@PathVariable int id){

        return cartService.delete(id);

    }
}
