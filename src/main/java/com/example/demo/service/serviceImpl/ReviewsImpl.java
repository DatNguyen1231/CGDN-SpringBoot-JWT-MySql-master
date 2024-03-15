package com.example.demo.service.serviceImpl;

import com.example.demo.model.Dto.Messenger;
import com.example.demo.model.Dto.ReviewsDto;
import com.example.demo.model.entity.DAOUser;
import com.example.demo.model.entity.Reviews;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReviewsRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewsImpl implements ReviewsService {
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Messenger messenger;
    @Override
    public ResponseEntity<?> add(ReviewsDto reviewsDto) {

        try {
            Reviews reviews = new Reviews();

            reviews.setRating(reviewsDto.getRating());
            reviews.setDateReview(reviews.getDateReview());
            reviews.setComment(reviews.getDateReview());

            reviews.setUser(userRepository.findById(reviewsDto.getId_user()).orElse(null));
            reviews.setProduct(productRepository.findById(reviewsDto.getId_product()).orElse(null));
            reviewsRepository.save(reviews);
            messenger.setMessenger(" add reviews successfully.");
            return new ResponseEntity<>(messenger, HttpStatus.OK);
        }catch (Exception e){

            messenger.setMessenger("add reviews error");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        }







    }

    @Override
    public ResponseEntity<?> get(Long productID,Long userId) {

        return null;
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        return null;
    }
}
