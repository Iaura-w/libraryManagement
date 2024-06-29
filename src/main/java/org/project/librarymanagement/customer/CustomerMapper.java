package org.project.librarymanagement.customer;

import org.project.librarymanagement.customer.dto.CustomerRequestDto;
import org.project.librarymanagement.customer.dto.CustomerResponseDto;
import org.project.librarymanagement.loan.LoanDto;
import org.project.librarymanagement.loan.LoanMapper;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerResponseDto mapFromCustomerToCustomerResponseDto(Customer customer) {
        List<LoanDto> loanDtos = new ArrayList<>();
        if (customer.loanHistory() != null) {
            loanDtos = customer.loanHistory()
                    .stream()
                    .map(LoanMapper::mapFromLoanToLoanDto)
                    .toList();
        }

        return CustomerResponseDto.builder()
                .id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .loanHistory(loanDtos)
                .build();

    }

    public static Customer mapFromCustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .firstName(customerRequestDto.firstName())
                .lastName(customerRequestDto.lastName())
                .loanHistory(new ArrayList<>())
                .build();
    }
}
