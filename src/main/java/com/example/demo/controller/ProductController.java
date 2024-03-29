package com.example.demo.controller;

import com.example.demo.model.Dto.ProductDto;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@RequestMapping("/admin/productcar")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    //@RequestParam("productImage") MultipartFile fileProductImage
    @PostMapping("/admin/productcar/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto  ){
        return productService.add(productDto);
    }

    @GetMapping("/productcar/getall/{page}/{size}")
    public ResponseEntity<?> getAllProduct(@PathVariable int page,@PathVariable int size){
        return productService.getAll(page,size);
    }
    @GetMapping("/productcar/getsome/{page}/{size}/{idType}")
    public ResponseEntity<?> getSomeProduct(@PathVariable int page,@PathVariable int size,@PathVariable Long idType){
        return productService.getSome(page,size,idType);
    }
    @DeleteMapping("/admin/productcar/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return productService.delete(id);
    }
    @PutMapping("/admin/productcar/put/{id}")
    public ResponseEntity<?> put(@PathVariable long id,@RequestBody ProductDto productDto) {
        return productService.put(id,productDto);
    }

}
