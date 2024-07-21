package com.bookstore.api.dto;

import java.util.Set;
import java.util.UUID;

public record PublisherDTO (
        String name,
        Set<UUID> bookIds
){
}
