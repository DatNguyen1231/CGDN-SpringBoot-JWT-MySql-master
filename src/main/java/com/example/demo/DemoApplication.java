package com.example.demo;

//import com.example.demo.entity.*;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.ShoppingCartDetail;
import com.example.demo.model.entity.TypeProduct;
import com.example.demo.repositories.*;

import org.apache.log4j.Logger;
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
    TypeProducRepository typeProductRepository;
    @Autowired
    StatusShoppingCartRepository statusRepository;
    @Autowired
    ProductRepository repository;
    //private static final Logger logger = Logger.getLogger(DemoApplication.class);

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
//        //trường này người dùng tự thêm
//        addType("xe may1 ");
//        addType("Phu 1 ");
//        addType("PhỤ Kiện1 ");
//        System.out.println("Đã thêm type product ");




    }

    private void addType(String type) {
        TypeProduct typeProduct = new TypeProduct();
        typeProduct.setNameType(type);
        typeProductRepository.save(typeProduct);
    }
    private void AddRole(String role){
        Role addRole =new Role();
        addRole.setRole(role);
        roleRepository.save(addRole);
    }
}
///https://www.canva.com/design/DAF-gmWCTBE/vTc64g4YqCIDAqKEYmRRKg/edit