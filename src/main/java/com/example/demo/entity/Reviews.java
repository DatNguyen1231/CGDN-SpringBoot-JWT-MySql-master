package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Reviews")
@Data
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_Product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_User", nullable = false)
    private DAOUser user;

    private int Rating;
    private String Comment;
    private String dateReview;
}
