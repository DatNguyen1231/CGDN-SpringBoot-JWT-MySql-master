package com.example.demo.service.serviceIplm;

import com.example.demo.OTD.CartOTD;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceimpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ResponseEntity<?> upload(CartOTD cartOTD) {

        Cart cart = new Cart();
        cart.setPurchaseDate(cartOTD.getPurchaseDate());
        cart.setQuantityCart(cartOTD.getQuantityCart());
        cart.setStatus(cartOTD.getStatus());
        cart.setUserId(cartOTD.getUserId());
       // cart.setproductId(cartOTD.getProductID());
        cart.setProductId(cartOTD.getProductID());
        //lấy thông tin
     //   User user =userRepository.findById(cartOTD.getUserId()).orElse(null);
       // Product product = productRepository.findById(cartOTD.getProductId()).orElse(null);

     //   if (user != null && product != null) {

            return new ResponseEntity<>(cartRepository.save(cart), HttpStatus.OK);
   //     }
  //      else {
 //           return null;
//        }
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        cartRepository.deleteById(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> put(int id, CartOTD cart) {

        Optional<Cart> cartUpdat =   cartRepository.findById(id);
        return cartUpdat.map(update -> {
            // Cập nhật thông tin người dùng
            update.setStatus(cart.getStatus());
            update.setPurchaseDate(cart.getPurchaseDate());
            update.setQuantityCart(cart.getQuantityCart());


            // Lưu thông tin người dùng đã cập nhật vào cơ sở dữ liệu
            cartRepository.save(update);
            return ResponseEntity.ok(update);
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Override
    public ResponseEntity<List<?>> findByUserIdAndStatus(int userId,int status) {


        return new ResponseEntity<>(cartRepository. findByUserIdAndStatus(userId,status), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<?>> findByStatus(int status) {
        return new ResponseEntity<>(cartRepository. findByStatus(status), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<?>> findByUserId(int UserId) {
        return new ResponseEntity<>(cartRepository.findByUserId(UserId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<?>> getAllCart() {
        return new ResponseEntity<>(cartRepository.findAll(), HttpStatus.OK);
    }
}
