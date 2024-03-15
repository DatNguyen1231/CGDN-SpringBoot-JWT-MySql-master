package com.example.demo.repositories;

import com.example.demo.model.entity.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<DAOUser, Long> {

    DAOUser findByUsername(String username);
}