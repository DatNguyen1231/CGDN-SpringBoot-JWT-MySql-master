package com.example.demo.Dto;

import com.example.demo.entity.DAOUser;
import com.example.demo.entity.Product;
import lombok.Data;

import javax.persistence.*;

@Data
public class ReviewsDto {

    private Long id;
    private Product product;
    private long id_user;
    private int Rating;
    private String Comment;
    private String dateReview;
}
