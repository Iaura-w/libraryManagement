package org.project.librarymanagement.loan;

public class LoanMapper {

    public static LoanDto mapFromLoanToLoanDto(Loan loan) {
        return LoanDto.builder()
                .bookAuthor(loan.book().author())
                .bookTitle(loan.book().title())
                .bookIsbn(loan.book().isbn())
                .borrowDate(loan.borrowDate())
                .returnDate(loan.returnDate())
                .build();
    }
}
