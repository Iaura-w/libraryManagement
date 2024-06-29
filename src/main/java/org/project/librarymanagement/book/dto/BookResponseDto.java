package org.project.librarymanagement.book.dto;

import lombok.Builder;

@Builder
public record BookResponseDto(
        String id,
        String author,
        String title,
        String isbn,
        boolean isAvailable
) {
}
