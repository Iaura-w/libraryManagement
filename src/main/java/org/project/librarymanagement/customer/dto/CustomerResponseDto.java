package org.project.librarymanagement.customer.dto;

import lombok.Builder;
import org.project.librarymanagement.loan.Loan;

import java.util.List;

@Builder
public record CustomerResponseDto(
        String id,
        String firstName,
        String lastName,
        List<Loan> loanHistory
) {
}
