package com.example.demo.controller;

import com.example.demo.Dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/productcar")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
        return productService.add(productDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllProductCar(){
        return productService.get();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return productService.delete(id);
    }
}
