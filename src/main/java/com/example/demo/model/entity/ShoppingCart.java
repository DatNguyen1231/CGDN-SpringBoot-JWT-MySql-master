package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ShoppingCart")
@Getter
@Setter
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalPriceCart;
    private String dateCreated;

    //1 đang chờ trong giỏ hàng user ,2 đang chờ duyêt, 3 đã duyệt , 4đã bị hủy;
    private int status;

    @ManyToOne
    @JoinColumn(name = "id_Users", nullable = false)
    private DAOUser user;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ShoppingCartId")
    private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<>();


}
