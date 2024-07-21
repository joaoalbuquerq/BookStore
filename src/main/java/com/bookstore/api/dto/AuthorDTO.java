package com.bookstore.api.dto;

import java.util.Set;
import java.util.UUID;

public record AuthorDTO(
        String name,
        Set<UUID> bookIds
) {
}
