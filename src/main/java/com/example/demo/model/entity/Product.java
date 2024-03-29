package com.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    private long quantity;
    private float Discount;
    // 1:HonDa 2:Ducati 3:Yamaha 4:kawasaki     5:đầu nhớt & Hóa chất 6:phụ kiện xe
    private int detailType;
    @ManyToOne
    @JoinColumn(name = "id_TypeCar", nullable = false)
    private TypeProduct typeProduct;
    @Type(type = "text")
    @Column(name = "describe", columnDefinition = "TEXT")
    private String describe;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productMoto_id")
// @Fetch(FetchMode.SUBSELECT)
    private List<Img> images = new ArrayList<>();

}

