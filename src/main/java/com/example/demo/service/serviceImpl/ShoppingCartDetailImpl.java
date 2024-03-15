package com.example.demo.service.serviceImpl;

import com.example.demo.model.entity.ShoppingCart;
import com.example.demo.model.entity.ShoppingCartDetail;
import com.example.demo.repositories.ShoppingCartDetailRepository;
import com.example.demo.repositories.ShoppingCartRepository;
import com.example.demo.service.ShoppingCartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartDetailImpl implements ShoppingCartDetailService {
    @Autowired
    ShoppingCartDetailRepository shoppingCartDetailRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ResponseEntity<?> Add(Long shoppingCartId, ShoppingCartDetail shoppingCartDetail) {
        // Lấy đối tượng ShoppingCart từ cơ sở dữ liệu
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElse(null);

        // Kiểm tra xem ShoppingCart có tồn tại không và trạng thái phải chưa thanh toán tức là 0
        if (shoppingCart != null && shoppingCart.getStatus()==0) {
            // Thêm ShoppingCartDetail vào danh sách shoppingCartDetails của ShoppingCart
            shoppingCart.getShoppingCartDetails().add(shoppingCartDetail);
            // Lưu lại thay đổi vào cơ sở dữ liệu
            shoppingCartRepository.save(shoppingCart);
        } else {
            // Xử lý khi ShoppingCart không tồn tại
            System.out.println("Không tìm thấy ShoppingCart có id: " + shoppingCartId);
        }

    //    messenger.setMessenger(" add Role successfully.");

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> deltete(long id) {
        return null;
    }
}
