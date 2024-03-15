package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repositories.*;
import com.example.demo.service.DescribeProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DescribeProductRepository describeRepository;
    @Autowired
    TypeProducRepository typeProductRepository;
    @Autowired
    StatusShoppingCartRepository statusRepository;
    @Autowired
    ProductRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    //thêm dữ liệu test
    //thêm 1 lần r tắt
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

//   //phải thêm dầu tiên người dùng k tự thêm được
//        System.out.println("hihi");
//        AddRole("ADMIN");
//        AddRole("USER");
//       System.out.println("Đã thêm role ");
//
//
//        AddStatus("Giỏ hàng user");
//        AddStatus("Chờ duyệt");
//        AddStatus("Đã duyệt");
//        System.out.println("Đã thêm Status ");
//
//        //trường này người dùng tự thêm
//        addType("xe may1 ");
//        addType("Phu 1 ");
//        addType("PhỤ Kiện1 ");
//        System.out.println("Đã thêm type product ");
//
//        Adddescribe("100cc1");
//        Adddescribe("100c1c");
//        Adddescribe("Guong1");
//        System.out.println("Đã thêm describe ");


    }

    private void addType(String type) {
        TypeProduct typeProduct = new TypeProduct();
        typeProduct.setNameType(type);
        typeProductRepository.save(typeProduct);
    }

    private void AddRole(String role) {
        Role addRole = new Role();
        addRole.setRole(role);
        roleRepository.save(addRole);
    }
    private void Adddescribe(String scribe) {
        DescribeProduct describeProduct = new  DescribeProduct();
        describeProduct.setDescribe(scribe);
        describeRepository.save(describeProduct);
    }
    private void AddStatus(String status) {
        StatusShoppingCart statusshoppingCart = new StatusShoppingCart();
        statusshoppingCart.setNameStatus(status);
        statusRepository.save(statusshoppingCart);
    }
}
