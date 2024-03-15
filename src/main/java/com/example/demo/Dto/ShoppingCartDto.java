package com.example.demo.Dto;

import com.example.demo.entity.ShoppingCart;
import lombok.Data;

@Data
public class ShoppingCartDto {


    private Long quantityCart;
    private String dateCreated;

    private Long id_user;
    private Long id_product;
    private Long id_StatusShoppingCart;


//
//    private UserDTO dto;
//
//    ShoppingCartDto(ShoppingCart cart){
//        this.dto  = new UserDTO(cart.getUser());
//    }

}
