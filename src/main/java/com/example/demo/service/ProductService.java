package com.example.demo.service;

import com.example.demo.model.Product;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import javax.mail.Multipart;
import java.util.List;
import javax.mail.Multipart;
@Service
public interface ProductService {

    ResponseEntity<List<?>>  findByName(String name);
    ResponseEntity<?>  findByID(int id);

    public ResponseEntity<?> uploadProduct(Product product );
    public        ResponseEntity<?>delete(int id);
    public       ResponseEntity<?>put(int id,Product product);
    ResponseEntity<List<?>> getAllProducts();
}
