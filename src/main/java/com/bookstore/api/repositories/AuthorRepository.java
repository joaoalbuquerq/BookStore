package com.bookstore.api.repositories;

import com.bookstore.api.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Author findAuthorByName(String name);
}
