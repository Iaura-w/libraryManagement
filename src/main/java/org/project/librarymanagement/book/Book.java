package org.project.librarymanagement.book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("books")
public record Book(
        @Id String id,
        @Field("author") String author,
        @Field("title") String title,
        @Field("available") boolean available
) {
    public Book(String title, String author) {
        this(null, title, author, true);
    }
}
