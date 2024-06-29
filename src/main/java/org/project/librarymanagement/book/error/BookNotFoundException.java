package org.project.librarymanagement.book.error;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String bookId) {
        super(String.format("Book with id '%s' not found", bookId));
    }
}
