package com.example.demo.service;

import com.example.demo.model.entity.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<?> add(Role role);
}
