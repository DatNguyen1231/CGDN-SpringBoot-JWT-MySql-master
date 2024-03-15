package com.example.demo.service.serviceImpl;

import com.example.demo.Dto.Messenger;
import com.example.demo.entity.DescribeProduct;
import com.example.demo.repositories.DescribeProductRepository;
import com.example.demo.service.DescribeProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DescribeProductImpl implements DescribeProductSevice {
    @Autowired
    DescribeProductRepository productRepository;
    @Autowired
    Messenger messenger;
    @Override
    public ResponseEntity<?> add(DescribeProduct describeProduct) {
        // Kiểm tra xem TypeProductCar với cùng một tên đã tồn tại chưa
        if ( productRepository.existsByDescribe(describeProduct.getDescribe())  ) {
            messenger.setMessenger("DescribeProductal ready exists.");
            return new ResponseEntity<>(messenger, HttpStatus.CONFLICT);
        }
        productRepository.save(describeProduct);
        messenger.setMessenger(" add DescribeProduct successfully.");

        return new ResponseEntity<>(messenger, HttpStatus.OK);
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

    @Override
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }
}
