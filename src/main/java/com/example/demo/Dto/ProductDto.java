package com.example.demo.Dto;

import com.example.demo.entity.Img;
import com.example.demo.entity.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private long price;
    private long quantity;
    private int discount;
    private long idTypeProduct;
    private long idDescribeProduct;
    private int sale;
    private List<Img> images = new ArrayList<>();

   
}
