package com.example.demo.model.Dto;

import com.example.demo.model.entity.ShoppingCartDetail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShoppingCartDto {



    private String dateCreated;

    private long totalPriceCart;
    private Long id_user;
    private int status;
    private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<>();
//
//    private UserDTO dto;
//
//    ShoppingCartDto(ShoppingCart cart){
//        this.dto  = new UserDTO(cart.getUser());
//    }

}
