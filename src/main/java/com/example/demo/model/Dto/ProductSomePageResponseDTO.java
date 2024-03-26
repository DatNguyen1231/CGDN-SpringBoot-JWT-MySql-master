package com.example.demo.model.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSomePageResponseDTO {

    private List<ProductSomeReturnDto> productSomeReturns;
    private int page;
    private int size;
    private long totalElements;
    private long totalPages;

}

