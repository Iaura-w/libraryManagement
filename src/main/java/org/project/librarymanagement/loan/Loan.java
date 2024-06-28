package org.project.librarymanagement.loan;

import org.project.librarymanagement.book.Book;
import org.project.librarymanagement.customer.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "loans")
public record Loan(
        @Id String id,
        @DBRef Book book,
        @DBRef Customer customer,
        LocalDateTime borrowDate,
        LocalDateTime returnDate
) {
    public Loan(Book book, Customer customer, LocalDateTime borrowDate) {
        this(null, book, customer, borrowDate, null);
    }
}