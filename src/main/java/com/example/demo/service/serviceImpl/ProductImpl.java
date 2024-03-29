package com.example.demo.service.serviceImpl;

import com.example.demo.model.Dto.*;
import com.example.demo.model.entity.Product;

import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.TypeProducRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    Messenger messenger;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TypeProducRepository typeProductRepository;

    @Override
    public ResponseEntity<?> add(ProductDto dto) {

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setQuantity(dto.getQuantity());
            product.setDetailType(dto.getDetailType());
            product.setImages(dto.getImages());
            product.setDiscount(dto.getDiscount());
            product.setDescribe(dto.getDescribe());
            product.setTypeProduct(typeProductRepository.findById(dto.getIdTypeProduct()).orElse(null));


            if (product.getTypeProduct() == null ) {
                messenger.setMessenger("Type  null");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }

            productRepository.save(product);
            messenger.setMessenger("add success");
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            messenger.setMessenger("error");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> getAll(int page,int size) {
        try {
            // Tạo Pageable để lấy danh sách sản phẩm trang hiện tại
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> productsPage = productRepository.findAll(pageable);

            // Tạo đối tượng ProductPageDTO và đặt các giá trị
            ProductPageDTO productPageDTO = new ProductPageDTO();
            productPageDTO.setProducts(productsPage.getContent());
            productPageDTO.setPage(productsPage.getNumber());
            productPageDTO.setSize(productsPage.getSize());
            productPageDTO.setTotalElements(productsPage.getTotalElements());
            productPageDTO.setTotalPages(productsPage.getTotalPages());

            return new ResponseEntity<>(productPageDTO, HttpStatus.OK);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getSome(int page, int size) {
        try {
            //lay 1 page tu csdl
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> productsPage = productRepository.findAll(pageable);

            //khoi tao 1 ProductSomePageReturnDTO de Response
            ProductSomePageResponseDTO productSomePageResponseDTO = new ProductSomePageResponseDTO();
            //set du lieu cho ProductSomePageResponseDTO

            //goi ham getProductSomePageResponseDTO de lay du lieu cho setProductSomeReturns
            productSomePageResponseDTO.setProductSomeReturns( getProductSomePageResponseDTO(productsPage) );

            productSomePageResponseDTO.setPage(productsPage.getNumber());
            productSomePageResponseDTO.setSize(productsPage.getSize());
            productSomePageResponseDTO.setTotalElements(productsPage.getTotalElements());
            productSomePageResponseDTO.setTotalPages(productsPage.getTotalPages());
            return new ResponseEntity<>(productSomePageResponseDTO, HttpStatus.OK);

        }catch (Exception e){  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
     //  return null;
    }
    //ham nay de lay vai thong tin tu product khonog lay het product
    private static  List<ProductSomeReturnDto> getProductSomePageResponseDTO(Page<Product> productsPage) {
        List<ProductSomeReturnDto> productSomeReturnDtos = new ArrayList<>();
        for (Product a : productsPage){
            ProductSomeReturnDto productSomeReturn = new ProductSomeReturnDto(a);
            productSomeReturnDtos.add(productSomeReturn);
        }
        return productSomeReturnDtos;
    }

    public ResponseEntity<?> getBy(String Type) {


        return new ResponseEntity<>(productRepository.findByTypeProduct_NameTypeContainingIgnoreCase(Type), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            messenger.setMessenger("delete ");
            System.out.println("delete successfully");
            return new ResponseEntity<>(messenger, HttpStatus.OK);

        }
        messenger.setMessenger("empty");
        return new ResponseEntity<>(messenger, HttpStatus.NOT_FOUND);
    }
    @Override
    public ResponseEntity<?> put(long id, ProductDto productDto) {
        try {
            Product product =productRepository.findById(id).orElse(null);
            if(product==null){
                messenger.setMessenger(" Product null");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }

            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());


            product.setDetailType(productDto.getDetailType());


          //  product.setImages(null);
           // product.setImages(productDto.getImages());

            product.setDiscount(productDto.getDiscount());
            product.setDescribe(productDto.getDescribe());
            product.setTypeProduct(typeProductRepository.findById(productDto.getIdTypeProduct()).orElse(null));

            if(product.getTypeProduct()==null){
                messenger.setMessenger("TypeProduct null");
                return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
            }
            productRepository.save(product);
            messenger.setMessenger("put success");
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            messenger.setMessenger("error");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }

    }
}
