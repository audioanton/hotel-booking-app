package org.nackademingroup.hotelbookingapp.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchDto {
    @NotNull(message = "Start date is required")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @Min(value = 1, message = "Total guests must be at least 1")
    private int totalGuests;
}
