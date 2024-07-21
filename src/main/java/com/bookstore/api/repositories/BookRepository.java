package com.bookstore.api.repositories;

import com.bookstore.api.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Book findBookByTitle(String titile);
}
