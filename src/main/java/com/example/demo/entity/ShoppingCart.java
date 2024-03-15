package com.example.demo.entity;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
@Table(name = "ShoppingCart")
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantityCart;
    private String dateCreated;
    @ManyToOne
    @JoinColumn(name = "id_Users", nullable = false)
    private DAOUser user;

    @ManyToOne
    @JoinColumn(name = "id_Product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_StatusShoppingCart", nullable = false)
    private StatusShoppingCart StatusShoppingCart;

}
