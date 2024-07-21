package com.bookstore.api.dto;

import java.util.UUID;

public record ReviewDTO(
        String comment,
        UUID bookId
) {
}
