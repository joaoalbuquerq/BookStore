package com.bookstore.api.services;

import com.bookstore.api.dto.PublisherDTO;
import com.bookstore.api.models.Publisher;
import com.bookstore.api.repositories.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    public List<Publisher> getAll(){
        return publisherRepository.findAll();
    }

    public Publisher getById(String id){
        return publisherRepository.findById(UUID.fromString(id)).orElseThrow(EntityNotFoundException::new);
    }

    public Publisher getByName(String name){
        return publisherRepository.findPublisherByName(name);
    }

    public Publisher create(PublisherDTO dto){

        var pub = new Publisher();
        pub.setName(dto.name());
        pub.setBooks(null);

        return publisherRepository.save(pub);
    }

    public Publisher update(PublisherDTO dto, String id){

        var pub = getById(id);

        if(dto.name() != null && !dto.name().isBlank())
            pub.setName(dto.name());

        if(dto.bookIds() != null && !dto.bookIds().isEmpty())
            pub.setBooks(null);

        return publisherRepository.save(pub);
    }

    public void delete(String id){
        var publisher = getById(id);
        publisherRepository.delete(publisher);
    }
}
