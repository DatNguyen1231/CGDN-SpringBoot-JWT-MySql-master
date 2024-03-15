package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TypeProduct")
@Data
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nameType;


}
