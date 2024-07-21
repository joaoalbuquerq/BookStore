package com.bookstore.api.controllers;

import com.bookstore.api.dto.PublisherDTO;
import com.bookstore.api.models.Publisher;
import com.bookstore.api.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    PublisherService service;

    @GetMapping
    public ResponseEntity<List<Publisher>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> findById(@PathVariable String id){
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Publisher> findByName(@PathVariable String name){
        return ResponseEntity.ok(service.getByName(name));
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody PublisherDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<Publisher> updatePublisher(@RequestBody PublisherDTO dto, @PathVariable String id){
        return ResponseEntity.ok(service.update(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublisher(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
