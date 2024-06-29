package org.project.librarymanagement.book;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Document("books")
public record Book(
        @Id String id,
        @Field("author") String author,
        @Field("title") String title,
        @Field("isbn") @Indexed(unique = true) String isbn,
        @Field("available") boolean isAvailable
) {
    public Book(String author, String title, String isbn) {
        this(null, author, title, isbn, true);
    }

    public Book(Book book, boolean isAvailable) {
        this(book.id, book.author, book.title, book.isbn, isAvailable);
    }
}
