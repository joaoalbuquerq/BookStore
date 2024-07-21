package com.bookstore.api.dto;

import java.util.Set;
import java.util.UUID;

public record BookDTO(
        String title,
        UUID publisherId,
        Set<UUID> authorsIds,
        String reviewComment
) {
}
