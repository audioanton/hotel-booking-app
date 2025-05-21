package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.*;
import org.nackademingroup.hotelbookingapp.models.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    public Optional<BookingDto> getBookingById(Long id);

    public Booking updateBooking(Booking booking);

    public void removeBooking(Booking booking);

    public BookingDto toBookingDto(Booking booking, BookingDetailsDto details, CustomerDto customer);

    public List<BookingDto> getBookings();

    public List<RoomDto> getAvailableRooms(RoomSearch roomSearch);
}
