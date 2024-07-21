package com.bookstore.api.controllers;

import com.bookstore.api.dto.BookDTO;
import com.bookstore.api.models.Book;
import com.bookstore.api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks(){
        return ResponseEntity.ok(service.findAllBooks());
    }

    @GetMapping("/id")
    public ResponseEntity<Book> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<Book> findByTitle(@PathVariable String title){
        return ResponseEntity.ok(service.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBook(dto));
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody BookDTO dto, @PathVariable String id){
        return ResponseEntity.ok(service.updateBook(dto, id));
    }

    @DeleteMapping
    public ResponseEntity deleteBook(@PathVariable String id){
        service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
