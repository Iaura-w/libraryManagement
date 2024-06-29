package org.project.librarymanagement.book.error;

public class BookCannotBeDeletedException extends RuntimeException {
    public BookCannotBeDeletedException(String bookId) {
        super(String.format("Book with id '%s' can not be deleted, because it is on loan history.", bookId));
    }
}
