package org.project.librarymanagement.book;

import org.project.librarymanagement.book.dto.BookRequestDto;
import org.project.librarymanagement.book.dto.BookResponseDto;

public class BookMapper {
    public static BookResponseDto mapFromBookToBookResponseDto(Book book) {
        return BookResponseDto.builder()
                .id(book.id())
                .title(book.title())
                .author(book.author())
                .isbn(book.isbn())
                .isAvailable(book.isAvailable())
                .build();
    }

    public static Book mapFromBookRequestDtoToBook(BookRequestDto bookRequestDto) {
        return Book.builder()
                .title(bookRequestDto.title())
                .author(bookRequestDto.author())
                .isbn(bookRequestDto.isbn())
                .isAvailable(true)
                .build();
    }
}
