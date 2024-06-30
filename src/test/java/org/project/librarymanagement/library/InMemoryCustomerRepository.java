package org.project.librarymanagement.library;

import org.project.librarymanagement.customer.Customer;
import org.project.librarymanagement.customer.CustomerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class InMemoryCustomerRepository implements CustomerRepository {

    Map<String, Customer> database = new HashMap<>();

    {
        database.put("idCustomer1", new Customer("idCustomer1", " Name 1", "Lastname 1", new ArrayList<>()));
        database.put("idCustomer2", new Customer("idCustomer2", "Name 2", "Lastname 2", new ArrayList<>()));
    }

    @Override
    public <S extends Customer> S save(S entity) {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(
                id.toString(),
                entity.firstName(),
                entity.lastName(),
                new ArrayList<>()
        );
        database.put(id.toString(), customer);
        return (S) customer;
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .toList();
    }

    @Override
    public Optional<Customer> findById(String string) {
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
    public List<Customer> findAll() {
        return database.values().stream().toList();
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public List<Customer> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public <S extends Customer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Customer> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
