package com.example.demo.model.Dto;

import com.example.demo.model.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductPageDTO {
    private List<Product> products;
    private int page;
    private int size;
    private long totalElements;
    private long totalPages;

}
