package com.example.demo.model.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String name;
    private String comment;
    private int rating;
    private String dateReview;

}
