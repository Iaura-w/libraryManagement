package org.project.librarymanagement.customer;

import org.project.librarymanagement.loan.Loan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("customers")
public record Customer(
        @Id String id,
        @Field("firstName") String firstName,
        @Field("lastName") String lastName,
        @DBRef List<Loan> loanHistory
) {
    public Customer(String firstName, String lastName) {
        this(null, firstName, lastName, new ArrayList<>());
    }
}
