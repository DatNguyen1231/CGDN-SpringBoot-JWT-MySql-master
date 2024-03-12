package com.example.demo.Dto;

import lombok.Data;

@Data
public class ShoppingCartDto {


    private Long quantityCart;
    private String dateCreated;

    private Long id_user;
    private Long id_product;
}
