package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDetailsDto {
    private Long id;
    @Min(value = 0, message = "Extra beds cannot be negative")
    private int extraBeds;
    @NotNull(message = "Room information is required")
    @Valid
    private RoomDto room;
}
