package com.bookstore.api.controllers;

import com.bookstore.api.dto.BookDTO;
import com.bookstore.api.dto.ReviewDTO;
import com.bookstore.api.models.Review;
import com.bookstore.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService service;

    @GetMapping
    public ResponseEntity<List<Review>> findAllReview(){
        return ResponseEntity.ok(service.findAllReview());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReview(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateBook(@RequestBody ReviewDTO dto, @PathVariable String id){
        return ResponseEntity.ok(service.updateReview(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReview(@PathVariable String id){
        service.deleteReview(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
