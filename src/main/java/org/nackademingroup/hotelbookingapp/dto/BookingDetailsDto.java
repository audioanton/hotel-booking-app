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
public class BookingDetailsDto {
    private Long id;
    private int extraBeds;
    private RoomDto room;

}
