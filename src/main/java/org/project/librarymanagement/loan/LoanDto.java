package org.project.librarymanagement.loan;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LoanDto(
        String bookTitle,
        String bookAuthor,
        String bookIsbn,
        LocalDateTime borrowDate,
        LocalDateTime returnDate
) {
}
