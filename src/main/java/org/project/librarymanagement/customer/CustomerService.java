package org.project.librarymanagement.customer;

import lombok.AllArgsConstructor;
import org.project.librarymanagement.customer.dto.CustomerRequestDto;
import org.project.librarymanagement.customer.dto.CustomerResponseDto;
import org.project.librarymanagement.customer.error.CustomerCannotBeDeletedException;
import org.project.librarymanagement.customer.error.CustomerNotFoundException;
import org.project.librarymanagement.loan.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream().map(CustomerMapper::mapFromCustomerToCustomerResponseDto)
                .toList();
    }

    public CustomerResponseDto getCustomer(String id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::mapFromCustomerToCustomerResponseDto)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerMapper.mapFromCustomerRequestDtoToCustomer(customerRequestDto);
        Customer save = customerRepository.save(customer);
        return CustomerMapper.mapFromCustomerToCustomerResponseDto(save);
    }

    public void deleteCustomer(String id) {
        if (loanRepository.existsByCustomerId(id)) {
            throw new CustomerCannotBeDeletedException(id);
        }
        customerRepository.deleteById(id);
    }

    public CustomerResponseDto updateCustomer(String id, CustomerRequestDto customerRequestDto) {
        Customer old = customerRepository.findById(id).orElse(null);
        if (old == null) {
            return addCustomer(customerRequestDto);
        }

        String newFirstName = old.firstName();
        String newLastName = old.lastName();

        if (customerRequestDto.firstName() != null) {
            newFirstName = customerRequestDto.firstName();
        }
        if (customerRequestDto.lastName() != null) {
            newLastName = customerRequestDto.lastName();
        }

        Customer update = Customer.builder()
                .id(old.id())
                .firstName(newFirstName)
                .lastName(newLastName)
                .loanHistory(old.loanHistory())
                .build();

        Customer save = customerRepository.save(update);
        return CustomerMapper.mapFromCustomerToCustomerResponseDto(save);
    }
}
