package com.bookstore.api.services;

import com.bookstore.api.dto.BookDTO;
import com.bookstore.api.models.Book;
import com.bookstore.api.models.Review;
import com.bookstore.api.repositories.AuthorRepository;
import com.bookstore.api.repositories.BookRepository;
import com.bookstore.api.repositories.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookrepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> findAllBooks(){
        return bookrepository.findAll();
    }

    public Book findById(String id){
        return bookrepository.findById(UUID.fromString(id)).orElseThrow(EntityNotFoundException::new);
    }

    public Book findByTitle(String title){
        return bookrepository.findBookByTitle(title);
    }

    public Book createBook(BookDTO dto){
        return bookrepository.save(montarBook(dto));
    }

    public Book updateBook(BookDTO dto, String id){
        var book = findById(id);

        if(dto.title() != null && !dto.title().isBlank())
            book.setTitle(dto.title());

        if(dto.publisherId() != null)
            book.setPublisher(publisherRepository.findById(dto.publisherId()).get());

        if(dto.authorsIds() != null && !dto.authorsIds().isEmpty())
            book.setAuthors(authorRepository.findAllById(dto.authorsIds()).stream().collect(Collectors.toSet()));

        if(dto.reviewComment() != null && !dto.reviewComment().isBlank())
            book.setReview(montarReview(dto, book));

        return bookrepository.save(book);
    }

    public void deleteBook(String id){
        bookrepository.delete(findById(id));
    }

    public Review montarReview(BookDTO dto, Book book){
        Review rv = new Review();
        rv.setComment(dto.reviewComment());
        rv.setBook(book);

        return rv;
    }

    public Book montarBook(BookDTO dto){
        Book book = new Book();
        book.setTitle(dto.title());
        book.setPublisher(publisherRepository.findById(dto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(dto.authorsIds()).stream().collect(Collectors.toSet()));
        book.setReview(montarReview(dto, book));

        return book;
    }
}
