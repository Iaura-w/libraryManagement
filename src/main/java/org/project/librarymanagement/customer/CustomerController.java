package org.project.librarymanagement.customer;

import lombok.AllArgsConstructor;
import org.project.librarymanagement.customer.dto.CustomerRequestDto;
import org.project.librarymanagement.customer.dto.CustomerResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable String id, @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequestDto));
    }
}
