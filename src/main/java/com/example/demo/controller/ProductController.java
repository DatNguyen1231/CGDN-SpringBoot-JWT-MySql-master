package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Multipart;
import java.io.Console;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> postUser(@RequestBody Product product) {

        return productService.uploadProduct(product);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
         return  productService.delete(id);
    }
//

    @GetMapping("/get")
    public ResponseEntity<List<?>>get1() {
        return  productService.getAllProducts();
    }
    @GetMapping("/get/id/{id}")
    public ResponseEntity<?>getByID(@PathVariable int id) {
        return  productService.findByID(id);
    }
    @GetMapping("/get/name/{name}")
    public ResponseEntity<List<?>>get(@PathVariable String name) {
        return  productService.findByName(name);
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody Product product) {
        return productService.put(id,product);
    }
}
