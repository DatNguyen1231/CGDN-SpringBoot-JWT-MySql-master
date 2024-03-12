package com.example.demo.service.serviceImpl;

import com.example.demo.Dto.Messenger;
import com.example.demo.Dto.ShoppingCartDto;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ShoppingCartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ShoppingCartImpl implements ShoppingCartService {
    @Autowired
    Messenger messenger;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<?> AddCart(ShoppingCartDto shoppingCartDto) {
        //tạo 1 shopping cart
        try {
            ShoppingCart shoppingCartNew = new ShoppingCart();

            //gán dữ liệu từ ShoppingCartDto cho ShoppingCart
            shoppingCartNew.setQuantityCart(shoppingCartDto.getQuantityCart());
            shoppingCartNew.setDateCreated(shoppingCartDto.getDateCreated());

            //láy setUser setUser từ id của ShoppingCartDto gửi về
            shoppingCartNew.setUser(userRepository.findById(shoppingCartDto.getId_user()).orElse(null));
            shoppingCartNew.setProduct(productRepository.findById(shoppingCartDto.getId_product()).orElse(null));

            if (shoppingCartNew.getUser() == null || shoppingCartNew.getProduct() == null) {
                messenger.setMessenger("User or product does not exist");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }

            shoppingCartRepository.save(shoppingCartNew);
            messenger.setMessenger("Add to cart successfully");

            return new ResponseEntity<>(messenger, HttpStatus.OK);

        } catch (Exception e) {
            messenger.setMessenger("error");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }
        //lưu

    }

    @Override
    public ResponseEntity<?> getAll() {

        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();

        //xóa mật khẩu
        for (ShoppingCart a : shoppingCarts) {
            a.getUser().setPassword(null);
        }
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deltete(long id) {
        if (shoppingCartRepository.existsById(id)) {
            shoppingCartRepository.deleteById(id);
            messenger.setMessenger("delete successfully");
            return new ResponseEntity<>(messenger, HttpStatus.OK);
        }
        messenger.setMessenger("empty");
        return new ResponseEntity<>(messenger, HttpStatus.NOT_FOUND);
    }
}
