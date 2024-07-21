package com.bookstore.api.services;

import com.bookstore.api.dto.AuthorDTO;
import com.bookstore.api.models.Author;
import com.bookstore.api.repositories.AuthorRepository;
import com.bookstore.api.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(String id){
        return authorRepository.findById(UUID.fromString(id)).orElseThrow(EntityNotFoundException::new);
    }

    public Author getAuthorByName(String name){
        return authorRepository.findAuthorByName(name);
    }

    public Author createAuthor(AuthorDTO dto){
        var author = new Author();
        author.setName(dto.name());
        author.setBooks(bookRepository.findAllById(dto.bookIds()).stream().collect(Collectors.toSet()));

        return authorRepository.save(author);
    }

    public Author updateAuthor(AuthorDTO dto, String id){
        var author = getAuthorById(id);

        if(dto.name() != null && !dto.name().isBlank())
            author.setName(dto.name());

        if(dto.bookIds() != null && !dto.bookIds().isEmpty())
            author.setBooks(bookRepository.findAllById(dto.bookIds()).stream().collect(Collectors.toSet()));

        return authorRepository.save(author);
    }

    public void deleteAuthor(String id){
        var author = getAuthorById(id);
        authorRepository.delete(author);
    }


}
