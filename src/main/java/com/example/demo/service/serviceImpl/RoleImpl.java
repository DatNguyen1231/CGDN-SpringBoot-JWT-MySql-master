package com.example.demo.service.serviceImpl;

import com.example.demo.Dto.Messenger;
import com.example.demo.entity.Role;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    Messenger messenger;
    @Override
    public ResponseEntity<?> add(Role role) {

        // Kiểm tra xem TypeProductCar với cùng một tên đã tồn tại chưa
        if ( roleRepository.findByRole(role.getRole()) != null ) {
            messenger.setMessenger("Role with the same name already exists.");
            return new ResponseEntity<>(messenger, HttpStatus.CONFLICT);
        }
        roleRepository.save(role);
        messenger.setMessenger(" add Role successf.");

        return new ResponseEntity<>(messenger, HttpStatus.OK);
    }
}
