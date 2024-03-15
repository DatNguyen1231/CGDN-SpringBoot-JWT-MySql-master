package com.example.demo.Dto;

import com.example.demo.entity.DAOUser;
import com.example.demo.entity.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;


    UserDTO(DAOUser user){
       this.id=user.getId();
        this.username=user.getUsername();
        this. fullName=user.getFullName();
        this. email=user.getEmail();
        this. phoneNumber=user.getPhoneNumber();
        this. address=user.getAddress();
        this. role=user.getRole();
    }
}
