package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ShoppingCartDetail")
@Data
public class ShoppingCartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float quantityCart;

    @ManyToOne
    @JoinColumn(name = "id_Product", nullable = false)
    private Product product;


}
