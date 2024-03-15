package com.example.demo.repositories;


import com.example.demo.entity.StatusShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusShoppingCartRepository extends JpaRepository<StatusShoppingCart,Long> {
}
