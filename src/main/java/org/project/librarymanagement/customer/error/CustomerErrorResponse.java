package org.project.librarymanagement.customer.error;

import org.springframework.http.HttpStatus;

public record CustomerErrorResponse(
        String message,
        HttpStatus status) {
}