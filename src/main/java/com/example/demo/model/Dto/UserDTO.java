package com.example.demo.model.Dto;

import com.example.demo.model.entity.DAOUser;
import com.example.demo.model.entity.Role;
import lombok.Data;

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
