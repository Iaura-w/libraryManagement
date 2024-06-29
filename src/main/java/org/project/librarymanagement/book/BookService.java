package org.project.librarymanagement.book;

import lombok.AllArgsConstructor;
import org.project.librarymanagement.book.dto.BookRequestDto;
import org.project.librarymanagement.book.dto.BookResponseDto;
import org.project.librarymanagement.book.error.BookCannotBeDeletedException;
import org.project.librarymanagement.book.error.BookNotFoundException;
import org.project.librarymanagement.loan.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::mapFromBookToBookResponseDto)
                .toList();
    }

    public BookResponseDto getBookById(String id) {
        return bookRepository.findById(id)
                .map(BookMapper::mapFromBookToBookResponseDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = BookMapper.mapFromBookRequestDtoToBook(bookRequestDto);
        Book save = bookRepository.save(book);
        return BookMapper.mapFromBookToBookResponseDto(save);
    }

    public void deleteBook(String id) {
        if (loanRepository.existsByBookId(id)) {
            throw new BookCannotBeDeletedException(id);
        }
        bookRepository.deleteById(id);
    }

    public BookResponseDto updateBook(String id, BookRequestDto bookRequestDto) {
        Book old = bookRepository.findById(id).orElse(null);
        if (old == null) {
            return addBook(bookRequestDto);
        }
        String newAuthor = old.author();
        String newTitle = old.title();
        String newIsbn = old.isbn();
        if (bookRequestDto.author() != null) {
            newAuthor = bookRequestDto.author();
        }
        if (bookRequestDto.title() != null) {
            newTitle = bookRequestDto.title();
        }
        if (bookRequestDto.isbn() != null) {
            newIsbn = bookRequestDto.isbn();
        }
        Book update = Book.builder()
                .id(old.id())
                .author(newAuthor)
                .title(newTitle)
                .isbn(newIsbn)
                .isAvailable(old.isAvailable())
                .build();

        Book save = bookRepository.save(update);
        return BookMapper.mapFromBookToBookResponseDto(save);
    }

    public Book changeBookAvailability(String id, boolean isAvailable) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        Book update = new Book(book, isAvailable);
        return bookRepository.save(update);
    }
}
