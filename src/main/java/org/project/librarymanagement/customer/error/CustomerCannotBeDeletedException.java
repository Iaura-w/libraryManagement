package org.project.librarymanagement.customer.error;

public class CustomerCannotBeDeletedException extends RuntimeException {
    public CustomerCannotBeDeletedException(String customerId) {
        super(String.format("Customer with id '%s' can not be deleted, because it is on loan history.", customerId));
    }
}
