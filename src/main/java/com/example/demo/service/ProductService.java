package com.example.demo.service;

import com.example.demo.model.Dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> add(ProductDto dto);
    ResponseEntity<?> getAll(int page, int size);
    ResponseEntity<?> getSome(int page, int size,Long idType);
    ResponseEntity<?> delete(long id);
    ResponseEntity<?> put(long id,ProductDto productDto);
}
