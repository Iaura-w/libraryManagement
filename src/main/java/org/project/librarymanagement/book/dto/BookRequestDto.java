package org.project.librarymanagement.book.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record BookRequestDto(
        @NotEmpty
        String author,
        @NotEmpty
        String title,
        @NotEmpty
        String isbn) {
}
