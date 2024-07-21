package com.bookstore.api.controllers;

import com.bookstore.api.dto.AuthorDTO;
import com.bookstore.api.models.Author;
import com.bookstore.api.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService service;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok(service.getAllAuthors());
    }

    @GetMapping("/id")
    public ResponseEntity<Author> getAuthorById(@PathVariable String id){
        return ResponseEntity.ok(service.getAuthorById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<Author> getAuthorByName(@PathVariable String name){
        return ResponseEntity.ok(service.getAuthorByName(name));
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAuthor(dto));
    }

    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody AuthorDTO dto, @PathVariable String id){
        return ResponseEntity.ok(service.updateAuthor(dto,id));
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteAuthor(@PathVariable String id){
        service.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
