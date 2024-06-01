package com.example.demo.OTD;

import com.example.demo.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Data
public class User_profileOTD {
    public Integer profileID;

    private String name;

    private String sex;

    private String birth;

    public String address;

    public String phoneNumber;

    public int userID;
}
