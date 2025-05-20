package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.BookingDetailsDto;
import org.nackademingroup.hotelbookingapp.dto.RoomDto;
import org.nackademingroup.hotelbookingapp.models.BookingDetails;
import org.nackademingroup.hotelbookingapp.repositories.BookingDetailsRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsServiceImp implements BookingDetailsService {

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Override
    public BookingDetailsDto toBookingDetailsDto(BookingDetails bookingDetails, RoomDto room) {
        return BookingDetailsDto.builder()
                .id(bookingDetails.getId())
                .extraBeds(bookingDetails.getExtraBeds())
                .room(room)
                .build();
    }
}
