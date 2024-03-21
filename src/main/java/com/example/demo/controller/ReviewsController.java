package com.example.demo.controller;

import com.example.demo.model.Dto.ProductDto;
import com.example.demo.model.Dto.ReviewsDto;
import com.example.demo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/reviews")
public class ReviewsController {
    @Autowired
    ReviewsService reviewsService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ReviewsDto reviewsDto){
        return reviewsService.add(reviewsDto);
    }
    @GetMapping("/get/{productId}")
    public ResponseEntity<?> get( @PathVariable long productId){
        return reviewsService.get(productId);

    }
}

