package com.example.demo.controller;

import com.example.demo.Dto.ShoppingCartDto;
import com.example.demo.entity.DescribeProduct;
import com.example.demo.service.DescribeProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/describe")
@RestController
public class DescribeProductController {

    @Autowired
    DescribeProductSevice describeProductSevice;


    @PostMapping("/add")

    public ResponseEntity<?> addTypeProduct(@RequestBody DescribeProduct describeProduct) {
        return describeProductSevice.add(describeProduct);
    }
    @GetMapping("/get")

    public ResponseEntity<?> get() {
        return describeProductSevice.get();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return describeProductSevice.delete(id);
    }
}
