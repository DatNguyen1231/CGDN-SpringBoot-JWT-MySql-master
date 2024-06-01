package com.example.demo.repository;



import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);


}

