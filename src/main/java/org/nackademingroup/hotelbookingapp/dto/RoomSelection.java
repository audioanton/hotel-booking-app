package org.nackademingroup.hotelbookingapp.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSelection {
    private int roomId;
    private int totalGuests;
    private Long customerId;
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
}
