package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "user_profiles")
public class User_profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileID;

    private String name;

    private String sex;

    private String birth;

    private String address;


    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;





    // Getters and setters
}