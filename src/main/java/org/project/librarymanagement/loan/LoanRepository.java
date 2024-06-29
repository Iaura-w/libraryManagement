package org.project.librarymanagement.loan;

import org.project.librarymanagement.book.Book;
import org.project.librarymanagement.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
    Optional<Loan> findByBookAndCustomerAndReturnDateIsNull(Book book, Customer customer);

    boolean existsByBookId(String bookId);

    boolean existsByCustomerId(String customerId);
}
