package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    private int quantityCart;

    private int status;

    private int userId; // Sửa tên thuộc tính thành userId

    private int productId;





    // Getters and setters
}
