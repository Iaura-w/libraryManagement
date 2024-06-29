package org.project.librarymanagement.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CustomerRequestDto(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName
) {
}
