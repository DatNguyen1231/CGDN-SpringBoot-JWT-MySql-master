package com.example.demo.repositories;

import com.example.demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByTypeProduct_NameTypeContainingIgnoreCase(String partialName);

//    @Query("select p from Product p where p.id = ?1")
//    Optional<Product> findByIdEquals(Long id);


}
