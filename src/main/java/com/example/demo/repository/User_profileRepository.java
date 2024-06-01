package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.User_profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service

public interface User_profileRepository extends JpaRepository<User_profile,Integer>{

    Optional<User_profile> findByUserId(Integer userId);

}
