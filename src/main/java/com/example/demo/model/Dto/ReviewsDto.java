package com.example.demo.model.Dto;

import com.example.demo.model.entity.Product;
import lombok.Data;

@Data
public class ReviewsDto {

    private Long id;
    private Long id_product;
    private Long id_user;
    private int Rating;
    private String Comment;
    private String dateReview;
}
