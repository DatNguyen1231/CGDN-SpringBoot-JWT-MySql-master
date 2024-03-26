package com.example.demo.service.serviceImpl;

import com.example.demo.model.Dto.Messenger;
import com.example.demo.model.Dto.ShoppingCartDto;
import com.example.demo.model.Dto.ShoppingCartDtoReturn;
import com.example.demo.model.entity.ShoppingCart;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ShoppingCartRepository;
import com.example.demo.repositories.StatusShoppingCartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    StatusShoppingCartRepository statusShoppingCartRepository;

    @Override
    public ResponseEntity<?> AddCart(ShoppingCartDto shoppingCartDto) {
        //tạo 1 shopping cart
        try {
            ShoppingCart shoppingCartNew = new ShoppingCart();
            //gán dữ liệu từ ShoppingCartDto cho ShoppingCart
            shoppingCartNew.setTotalPriceCart(shoppingCartDto.getTotalPriceCart());
            shoppingCartNew.setDateCreated(shoppingCartDto.getDateCreated());
            shoppingCartNew.setStatus(shoppingCartDto.getStatus());

            //láy dữ liệu từ db theo id của ShoppingCartDto gửi về
            shoppingCartNew.setUser(userRepository.findById(shoppingCartDto.getId_user()).orElse(null));
          //  shoppingCartNew.setShoppingCartDetails(shoppingCartDto.getShoppingCartDetails());
            //kiem tra dữ liệu có null k
            if (shoppingCartNew.getUser() == null ) {
                messenger.setMessenger("User  does not exist");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }
            //luu
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
        List<ShoppingCartDtoReturn> shoppingCartDtoReturns1 = new ArrayList<>();
        for (ShoppingCart a : shoppingCarts) {
            ShoppingCartDtoReturn shoppingCartDtoReturns = new ShoppingCartDtoReturn(a);
            shoppingCartDtoReturns1.add(shoppingCartDtoReturns);
        }
        return new ResponseEntity<>(shoppingCartDtoReturns1, HttpStatus.OK);

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
