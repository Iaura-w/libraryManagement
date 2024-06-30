package org.project.librarymanagement.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryServiceTest {

    InMemoryLoanRepository loanRepository;

    @BeforeEach
    void setUp() {
        loanRepository = new InMemoryLoanRepository();
    }

    @Test
    void when_borrow_should_return_status_borrow_success_if_is_available_and_customer_is_valid() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();

        // when
        BorrowStatus actual = libraryService.borrowBook("idBook1", "idCustomer1");

        // then
        assertEquals(BorrowStatus.BORROW_SUCCESS, actual);
        assertEquals(loanRepository.count(), 1);
    }

    @Test
    void when_borrow_should_return_status_book_not_found_if_not_exists() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();

        // when
        BorrowStatus actual = libraryService.borrowBook("invalidId", "idCustomer1");

        // then
        assertEquals(BorrowStatus.BOOK_NOT_FOUND, actual);
        assertEquals(loanRepository.count(), 0);
    }

    @Test
    void when_borrow_should_return_status_customer_not_found_if_not_exists() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();

        // when
        BorrowStatus actual = libraryService.borrowBook("idBook1", "invalidId");

        // then
        assertEquals(BorrowStatus.CUSTOMER_NOT_FOUND, actual);
        assertEquals(loanRepository.count(), 0);
    }

    @Test
    void when_borrow_should_return_status_book_not_available_if_availability_is_false() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();

        // when
        BorrowStatus actual = libraryService.borrowBook("idBook3", "idCustomer1");

        // then
        assertEquals(BorrowStatus.BOOK_NOT_AVAILABLE, actual);
        assertEquals(loanRepository.count(), 0);
    }

    @Test
    void when_return_should_return_status_book_returned_successfully_if_is_borrowed_and_customer_is_valid() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();
        BorrowStatus borrowStatus = libraryService.borrowBook("idBook1", "idCustomer1");
        assertEquals(BorrowStatus.BORROW_SUCCESS, borrowStatus);
        assertEquals(loanRepository.count(), 1);

        // when
        ReturnStatus actual = libraryService.returnBook("idBook1", "idCustomer1");

        // then
        assertEquals(ReturnStatus.BOOK_RETURNED_SUCCESSFULLY, actual);
        assertEquals(loanRepository.count(), 1);
    }

    @Test
    void when_return_should_return_status_book_not_found_if_book_with_id_not_exists() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();
        BorrowStatus borrowStatus = libraryService.borrowBook("idBook1", "idCustomer1");
        assertEquals(BorrowStatus.BORROW_SUCCESS, borrowStatus);
        assertEquals(loanRepository.count(), 1);

        // when
        ReturnStatus actual = libraryService.returnBook("invalidId", "idCustomer1");

        // then
        assertEquals(ReturnStatus.BOOK_NOT_FOUND, actual);
        assertEquals(loanRepository.count(), 1);
    }

    @Test
    void when_return_should_return_status_customer_not_found_if_customer_with_id_not_exists() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();
        BorrowStatus borrowStatus = libraryService.borrowBook("idBook1", "idCustomer1");
        assertEquals(BorrowStatus.BORROW_SUCCESS, borrowStatus);
        assertEquals(loanRepository.count(), 1);

        // when
        ReturnStatus actual = libraryService.returnBook("idBook1", "invalidId");

        // then
        assertEquals(ReturnStatus.CUSTOMER_NOT_FOUND, actual);
        assertEquals(loanRepository.count(), 1);
    }

    @Test
    void when_return_should_return_status_book_is_not_borrowed_if_book_is_available() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();
        BorrowStatus borrowStatus = libraryService.borrowBook("idBook1", "idCustomer1");
        assertEquals(BorrowStatus.BORROW_SUCCESS, borrowStatus);
        assertEquals(loanRepository.count(), 1);

        // when
        ReturnStatus actual = libraryService.returnBook("idBook2", "idCustomer1");

        // then
        assertEquals(ReturnStatus.BOOK_IS_NOT_BORROWED, actual);
        assertEquals(loanRepository.count(), 1);
    }


    @Test
    void when_return_should_return_status_customer_did_not_borrow_this_book_if_customer_is_invalid() {
        // given
        LibraryService libraryService = new LibraryServiceConfig(loanRepository).libraryServiceForTests();
        BorrowStatus borrowStatus = libraryService.borrowBook("idBook1", "idCustomer1");
        assertEquals(BorrowStatus.BORROW_SUCCESS, borrowStatus);
        assertEquals(loanRepository.count(), 1);

        // when
        ReturnStatus actual = libraryService.returnBook("idBook1", "idCustomer2");

        // then
        assertEquals(ReturnStatus.CUSTOMER_DID_NOT_BORROW_THIS_BOOK, actual);
        assertEquals(loanRepository.count(), 1);
    }
}