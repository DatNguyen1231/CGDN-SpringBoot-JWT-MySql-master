package com.example.demo.service.serviceImpl;

import com.example.demo.Dto.Messenger;
import com.example.demo.entity.Product;
import com.example.demo.repositories.ProductRepository;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    Messenger messenger;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ResponseEntity<?> add(Product productMoto) {
        return new ResponseEntity<>( productRepository.save(productMoto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> get() {
        return  new ResponseEntity<>( productRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            messenger.setMessenger("delete successfully");
            return  new ResponseEntity<>( messenger, HttpStatus.OK);
        }
        messenger.setMessenger("empty");
        return  new ResponseEntity<>( messenger,  HttpStatus.NOT_FOUND);
    }
}
