package com.bookstore.api.services;

import com.bookstore.api.dto.BookDTO;
import com.bookstore.api.dto.ReviewDTO;
import com.bookstore.api.models.Book;
import com.bookstore.api.models.Review;
import com.bookstore.api.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookService bookService;

    public List<Review> findAllReview(){
        return reviewRepository.findAll();
    }

    public Review findById(String id){
        return reviewRepository.findById(UUID.fromString(id)).orElseThrow(EntityNotFoundException::new);
    }

    public Review createReview(ReviewDTO dto){

        Review rv = new Review();

        if(dto.comment() != null && !dto.comment().isBlank())
            rv.setComment(dto.comment());

        if(dto.bookId() != null && !dto.bookId().toString().isBlank())
            rv.setBook(bookService.findById(dto.bookId().toString()));

        return reviewRepository.save(rv);
    }

    public Review updateReview(ReviewDTO dto, String id){
        var review = findById(id);

        if(dto.comment() != null && !dto.comment().isBlank())
            review.setComment(dto.comment());

        return reviewRepository.save(review);
    }

    public void deleteReview(String id){
        reviewRepository.delete(findById(id));
    }
}
