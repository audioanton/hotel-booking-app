package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.BookingDetailsDto;
import org.nackademingroup.hotelbookingapp.dto.RoomDto;
import org.nackademingroup.hotelbookingapp.models.BookingDetails;

public interface BookingDetailsService {
    public BookingDetailsDto toBookingDetailsDto(BookingDetails bookingDetails, RoomDto room);
}
