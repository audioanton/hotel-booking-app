package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDetailsDto {
    private Long id;
    
    @Min(value = 0, message = "Extra beds cannot be negative")
    private int extraBeds;
    
    @Valid
    @NotNull(message = "Room is required")
    private RoomDto room;

}
