package org.project.librarymanagement.customer.dto;

import lombok.Builder;
import org.project.librarymanagement.loan.LoanDto;

import java.util.List;

@Builder
public record CustomerResponseDto(
        String id,
        String firstName,
        String lastName,
        List<LoanDto> loanHistory
) {
}
