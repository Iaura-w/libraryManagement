package org.project.librarymanagement.library;

public class LibraryServiceConfig {
    private final InMemoryBookRepository inMemoryBookRepository;
    private final InMemoryCustomerRepository inMemoryCustomerRepository;
    private final InMemoryLoanRepository inMemoryLoanRepository;

    public LibraryServiceConfig(InMemoryLoanRepository inMemoryLoanRepository) {
        this.inMemoryBookRepository = new InMemoryBookRepository();
        this.inMemoryCustomerRepository = new InMemoryCustomerRepository();
        this.inMemoryLoanRepository = inMemoryLoanRepository;
    }

    LibraryService libraryServiceForTests() {
        return new LibraryService(inMemoryCustomerRepository, inMemoryBookRepository, inMemoryLoanRepository);
    }

}
