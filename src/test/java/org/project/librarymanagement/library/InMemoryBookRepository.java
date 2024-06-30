package org.project.librarymanagement.library;

import org.project.librarymanagement.book.Book;
import org.project.librarymanagement.book.BookRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class InMemoryBookRepository implements BookRepository {

    Map<String, Book> database = new HashMap<>();

    {
        database.put("idBook1", new Book("idBook1", "Author 1", "Title 1", "11111", true));
        database.put("idBook2", new Book("idBook2", "Author 2", "Title 2", "22222", true));
        database.put("idBook3", new Book("idBook2", "Author 3", "Title 3", "33333", false));
    }

    @Override
    public <S extends Book> S save(S entity) {
        Optional<Map.Entry<String, Book>> existingBookEntry = database.entrySet().stream()
                .filter(entry -> entry.getValue().isbn().equals(entity.isbn()))
                .findFirst();

        if (existingBookEntry.isPresent()) {
            Book existingBook = existingBookEntry.get().getValue();
            Book updatedBook = new Book(
                    existingBook.id(),
                    entity.author(),
                    entity.title(),
                    entity.isbn(),
                    entity.isAvailable()
            );
            database.put(existingBook.id(), updatedBook);
            return (S) updatedBook;
        } else {
            UUID id = UUID.randomUUID();
            Book newBook = new Book(
                    id.toString(),
                    entity.author(),
                    entity.title(),
                    entity.isbn(),
                    true
            );
            database.put(id.toString(), newBook);
            return (S) newBook;
        }
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .toList();
    }

    @Override
    public Optional<Book> findById(String string) {
        return Optional.ofNullable(database.get(string));
    }

    @Override
    public boolean existsById(String string) {
        return database.containsKey(string);
    }

    @Override
    public long count() {
        return database.size();
    }

    @Override
    public List<Book> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Book, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Book> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Book> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Book> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Book> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

}
