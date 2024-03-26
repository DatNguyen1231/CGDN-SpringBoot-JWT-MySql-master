package com.example.demo.model.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
This is class is required for creating a response containing the JWT to be returned to the user.
 */
@Getter
@Setter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;

    private String role;
    public JwtResponse(String token, String role) {
        this.token = token;
        this.role =role;
    }

//    public String getToken() {
//        return this.jwttoken;
//    }
}
