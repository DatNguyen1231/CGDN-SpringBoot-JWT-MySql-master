package com.example.demo.service.serviceIplm;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import javax.mail.Multipart;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public ResponseEntity<List<?>> findByName(String name) {
        return new ResponseEntity<>( productRepository.findByName(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByID(int id) {
        return new ResponseEntity<>( productRepository.findById(id), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> uploadProduct(Product product ) {
        return new ResponseEntity<>( productRepository.save(product), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?>delete(int id){
        productRepository.deleteById(id);
        return new ResponseEntity<>("upload ", HttpStatus.OK);
    }
// return existingUser.map(userToUpdate -> {
//        // Cập nhật thông tin người dùng
//        userToUpdate.setEmail(user.getEmail());
//        userToUpdate.setRole(user.getRole());
//        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
//        userRepository.save(userToUpdate);
//        return ResponseEntity.ok(userToUpdate);
//    }).orElseGet(() -> ResponseEntity.notFound().build());
    @Override
    public ResponseEntity<?> put(int id, Product product) {
        Optional<Product> producGetSql =   productRepository.findById(id);
        return producGetSql.map(update -> {
            // Cập nhật thông tin người dùng
            update.setName(product.getName());
            update.setPrice(product.getPrice());
           update.setQuantity(product.getQuantity());
            update.setImageData(product.getImageData());

            // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
            productRepository.save(update);
            return ResponseEntity.ok(update);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Override
    public ResponseEntity<List<?>> getAllProducts() {

        return new ResponseEntity<>(productRepository.findAll(),HttpStatus.OK);
    }
}
