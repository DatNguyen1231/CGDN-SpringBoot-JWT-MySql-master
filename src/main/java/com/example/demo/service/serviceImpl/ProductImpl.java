package com.example.demo.service.serviceImpl;

import com.example.demo.Dto.Messenger;
import com.example.demo.Dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.repositories.DescribeProductRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.TypeProducRepository;
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
    @Autowired
    TypeProducRepository typeProductRepository;
    @Autowired
    DescribeProductRepository describeProductRepository;
    @Override
    public ResponseEntity<?> add(ProductDto dto) {

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setQuantity(dto.getQuantity());
            product.setDiscount(dto.getDiscount());
            product.setImages(dto.getImages());
            product.setSale(dto.getSale());
            product.setTypeProduct(typeProductRepository.findById(dto.getIdTypeProduct()).orElse(null));
            product.setDescribeProduct(describeProductRepository.findById(dto.getIdDescribeProduct()).orElse(null));

            if (product.getTypeProduct() == null || product.getDescribeProduct() == null){
                messenger.setMessenger("Type or describe null");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }

            productRepository.save(product);
            messenger.setMessenger("add success");
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e) {
            messenger.setMessenger("error");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<?> getBy(String Type) {
        return new ResponseEntity<>(productRepository.findByTypeProduct_NameTypeContainingIgnoreCase(Type), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            messenger.setMessenger("delete successfully");
            return new ResponseEntity<>(messenger, HttpStatus.OK);
        }
        messenger.setMessenger("empty");
        return new ResponseEntity<>(messenger, HttpStatus.NOT_FOUND);
    }
}
