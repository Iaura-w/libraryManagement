package org.project.librarymanagement.library;

import lombok.AllArgsConstructor;
import org.project.librarymanagement.book.Book;
import org.project.librarymanagement.book.BookRepository;
import org.project.librarymanagement.customer.Customer;
import org.project.librarymanagement.customer.CustomerRepository;
import org.project.librarymanagement.loan.Loan;
import org.project.librarymanagement.loan.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LibraryService {
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    @Transactional
    public BorrowStatus borrowBook(String bookId, String customerId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (book == null) {
            return BorrowStatus.BOOK_NOT_FOUND;
        }
        if (customer == null) {
            return BorrowStatus.CUSTOMER_NOT_FOUND;
        }
        if (!book.isAvailable()) {
            return BorrowStatus.BOOK_NOT_AVAILABLE;
        }

        Book updatedBook = new Book(book, false);
        bookRepository.save(updatedBook);

        Loan loan = new Loan(updatedBook, customer, LocalDateTime.now());
        Loan savedLoan = loanRepository.save(loan);

        customer.loanHistory().add(savedLoan);
        customerRepository.save(customer);
        return BorrowStatus.BORROW_SUCCESS;
    }

    @Transactional
    public ReturnStatus returnBook(String bookId, String customerId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (book == null) {
            return ReturnStatus.BOOK_NOT_FOUND;
        }
        if (customer == null) {
            return ReturnStatus.CUSTOMER_NOT_FOUND;
        }
        if (book.isAvailable()) {
            return ReturnStatus.BOOK_IS_NOT_BORROWED;
        }

        Loan loan = loanRepository.findByBookAndCustomerAndReturnDateIsNull(book, customer).orElse(null);
        if (loan == null) {
            return ReturnStatus.CUSTOMER_DID_NOT_BORROW_THIS_BOOK;
        }

        Book updatedBookAvailability = new Book(book, true);
        bookRepository.save(updatedBookAvailability);

        Loan updatedLoan = new Loan(loan.id(), loan.book(), loan.customer(), loan.borrowDate(), LocalDateTime.now());

        loanRepository.save(updatedLoan);

        return ReturnStatus.BOOK_RETURNED_SUCCESSFULLY;
    }
}
