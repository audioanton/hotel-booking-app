package org.nackademingroup.hotelbookingapp.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSelectionDto {
    @NotNull(message = "Room ID is required")
    private Long roomId;
    
    @Min(value = 1, message = "Total guests must be at least 1")
    private int totalGuests;
    
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    @NotNull(message = "Start date is required")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
}
