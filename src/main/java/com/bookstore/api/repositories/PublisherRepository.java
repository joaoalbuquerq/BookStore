package com.bookstore.api.repositories;

import com.bookstore.api.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    Publisher findPublisherByName(String name);
}
