package com.example.demo.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ShoppingCartDetail")
@Getter
@Setter
public class ShoppingCartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float quantityCart;

    @ManyToOne
    @JoinColumn(name = "id_Product", nullable = false)
    private Product product;


}
