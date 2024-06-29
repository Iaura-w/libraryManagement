package org.project.librarymanagement.book.error;

import org.springframework.http.HttpStatus;

public record BookErrorResponse(
        String message,
        HttpStatus status) {
}