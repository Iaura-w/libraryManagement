package org.project.librarymanagement.customer;

import org.project.librarymanagement.customer.dto.CustomerRequestDto;
import org.project.librarymanagement.customer.dto.CustomerResponseDto;

public class CustomerMapper {

    public static CustomerResponseDto mapFromCustomerToCustomerResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .loanHistory(customer.loanHistory())
                .build();

    }

    public static Customer mapFromCustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .firstName(customerRequestDto.firstName())
                .lastName(customerRequestDto.lastName())
                .build();
    }
}
