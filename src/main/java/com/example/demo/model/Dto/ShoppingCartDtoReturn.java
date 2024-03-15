package com.example.demo.model.Dto;

import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.ShoppingCart;
import com.example.demo.model.entity.ShoppingCartDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCartDtoReturn {
    private long idCart;
    private String dateCreated;
    private UserDTO userDTO;

    private long totalPriceCart;
    private int status;
    private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<>();


   public ShoppingCartDtoReturn(ShoppingCart shoppingCart){
        this.idCart=shoppingCart.getId();
        this.status=shoppingCart.getStatus();
        this.totalPriceCart =shoppingCart.getTotalPriceCart();
        this.dateCreated=shoppingCart.getDateCreated();
        this.userDTO=new UserDTO(shoppingCart.getUser());
        this.shoppingCartDetails = shoppingCart.getShoppingCartDetails();
    }
}
