package org.nackademingroup.hotelbookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingDetailsDto bookingDetails;
    private CustomerDto customer;
}
