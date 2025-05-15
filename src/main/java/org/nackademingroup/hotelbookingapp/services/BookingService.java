package org.nackademingroup.hotelbookingapp.services;

import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getMockBookings(){
        List<Booking> mockBookings = new ArrayList<>();

        mockBookings.add(Booking.builder()
                .id(1L)
                .start_date(LocalDate.parse("2025-05-12"))
                .end_date(LocalDate.parse("2025-05-14")).build());

        mockBookings.add(Booking.builder()
                .id(2L)
                .start_date(LocalDate.parse("2025-05-16"))
                .end_date(LocalDate.parse("2025-05-17")).build());

    return mockBookings;
    }
}
