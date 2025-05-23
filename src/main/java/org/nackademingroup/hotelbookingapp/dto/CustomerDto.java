package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    @NotNull(message = "Customer must have a name")
    @Size(min = 1, max = 50, message = "Customer name must be between 1 and 50 characters")
    private String name;
    @NotNull(message = "Customer must have a phone number")
    @Size(min = 1, max = 15, message = "Customer phone number must be between 1 and 15 characters")
    private String phoneNumber;
}
