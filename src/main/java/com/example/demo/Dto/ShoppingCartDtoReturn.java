package com.example.demo.Dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.StatusShoppingCart;
import lombok.Data;

@Data
public class ShoppingCartDtoReturn {
    private long idCart;
    private Long quantityCart;
    private String dateCreated;

    private UserDTO userDTO;
    private Product product;
    private StatusShoppingCart statusShoppingCart;

   public ShoppingCartDtoReturn(ShoppingCart shoppingCart){
        this.idCart=shoppingCart.getId();
        this.quantityCart=shoppingCart.getQuantityCart();
        this.dateCreated=shoppingCart.getDateCreated();
        this.userDTO=new UserDTO(shoppingCart.getUser());
        this.product=shoppingCart.getProduct();
        this.statusShoppingCart=shoppingCart.getStatusShoppingCart();
    }
}
