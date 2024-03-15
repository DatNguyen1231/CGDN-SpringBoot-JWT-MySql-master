package com.example.demo.repositories;

import com.example.demo.entity.DescribeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DescribeProductRepository extends JpaRepository<DescribeProduct,Long> {
    DescribeProduct findByDescribe(String describe);
    Boolean existsByDescribe(String describe);
}
