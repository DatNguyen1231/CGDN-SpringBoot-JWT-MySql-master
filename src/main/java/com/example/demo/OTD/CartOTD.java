package com.example.demo.OTD;

import lombok.Data;

import java.util.Date;

@Data
public class CartOTD {
    private int id;
    private Date purchaseDate;
    private int quantityCart;
    private int  status;
    private int userId;    // chỉ chứa id của User
    private int productID;

}
