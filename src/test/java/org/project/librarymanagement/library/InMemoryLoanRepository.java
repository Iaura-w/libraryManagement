package org.project.librarymanagement.library;

import org.project.librarymanagement.book.Book;
import org.project.librarymanagement.customer.Customer;
import org.project.librarymanagement.loan.Loan;
import org.project.librarymanagement.loan.LoanRepository;
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

public class InMemoryLoanRepository implements LoanRepository {

    Map<String, Loan> database = new HashMap<>();

    @Override
    public Optional<Loan> findByBookAndCustomerAndReturnDateIsNull(Book book, Customer customer) {
        for (Loan loan : database.values()) {
            if (loan.book().equals(book) && loan.customer().equals(customer) && loan.returnDate() == null) {
                return Optional.of(loan);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsByBookId(String bookId) {
        for (Loan loan : database.values()) {
            if (loan.book().equals(bookId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByCustomerId(String customerId) {
        for (Loan loan : database.values()) {
            if (loan.customer().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <S extends Loan> S save(S entity) {
        Optional<Map.Entry<String, Loan>> existingLoanEntry = database.entrySet().stream()
                .filter(entry -> entry.getValue().book().equals(entity.book())
                        && entry.getValue().customer().equals(entity.customer())
                        && entry.getValue().borrowDate().equals(entity.borrowDate()))
                .findFirst();

        if (existingLoanEntry.isPresent()) {
            Loan existingLoan = existingLoanEntry.get().getValue();
            Loan updatedLoan = new Loan(
                    existingLoan.id(),
                    entity.book(),
                    entity.customer(),
                    entity.borrowDate(),
                    entity.returnDate()
            );
            database.put(existingLoan.id(), updatedLoan);
            return (S) updatedLoan;
        } else {
            UUID id = UUID.randomUUID();
            Loan newLoan = new Loan(
                    id.toString(),
                    entity.book(),
                    entity.customer(),
                    entity.borrowDate(),
                    entity.returnDate()
            );
            database.put(id.toString(), newLoan);
            return (S) newLoan;
        }
    }

    @Override
    public <S extends Loan> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .toList();
    }

    @Override
    public Optional<Loan> findById(String string) {
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
    public List<Loan> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public void delete(Loan entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Loan> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Loan> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Loan> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Loan> findAllById(Iterable<String> strings) {
        return List.of();
    }


    @Override
    public <S extends Loan> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Loan> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Loan> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Loan> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Loan> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Loan> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Loan> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Loan> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Loan, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
