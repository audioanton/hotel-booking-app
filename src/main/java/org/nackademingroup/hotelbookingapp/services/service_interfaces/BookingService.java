package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.*;
import org.nackademingroup.hotelbookingapp.models.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    public BookingDto getBookingById(Long id);

    public void updateBookingExtraBeds(Long id, BookingDto bookingDto);

    public void updateBookingDates(Long id, BookingDto booking);

    public void validateDates(LocalDate startDate, LocalDate endDate);

    public void validateAvailability(Long id, LocalDate startDate, LocalDate endDate, Long roomId);

    public void removeBooking(Long id);

    public BookingDto toBookingDto(Booking booking, BookingDetailsDto details, CustomerDto customer);

    public List<BookingDto> getBookings();

    public List<RoomDto> getAvailableRooms(RoomSearchDto roomSearchDto);

    public void createBooking(RoomSelectionDto roomSelectionDto);

    public int getExtraBedsForBooking(int beds, RoomSelectionDto roomSelectionDto);
}