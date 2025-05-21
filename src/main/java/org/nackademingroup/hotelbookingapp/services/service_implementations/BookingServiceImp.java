package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.*;
import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.repositories.BookingRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomSizeService roomSizeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingDetailsService bookingDetailsService;
    @Autowired
    private CustomerService customerService;

    public List<BookingDto> getMockBookings() {


        RoomSizeDto sizeDto = RoomSizeDto.builder().size("Large").build();
        RoomDto roomDto = RoomDto.builder().name("The suit").roomSize(sizeDto).build();
        BookingDetailsDto detailsDto = BookingDetailsDto.builder().extraBeds(1).room(roomDto).build();
        CustomerDto customerDto = CustomerDto.builder().name("Antonio Larzon").build();
        BookingDto bookingDto = BookingDto.builder().bookingDetails(detailsDto).customer(customerDto).build();

        List<BookingDto> mockBookings = new ArrayList<>();

        mockBookings.add(bookingDto);

        return mockBookings;
    }

    public Optional<BookingDto> getBookingById(Long id) {
        return getMockBookings().stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst();
    }

    public Booking updateBooking(Booking booking) {
        return booking;
    }

    public void removeBooking(Booking booking) {

    }

    @Override
    public BookingDto toBookingDto(Booking booking, BookingDetailsDto details, CustomerDto customer) {
        return BookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .bookingDetails(details)
                .customer(customer)
                .build();
    }

    @Override
    public List<BookingDto> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(b -> {
                    RoomSizeDto roomSize = roomSizeService.toRoomSizeDto(b.getBookingDetails().getRoom().getRoomsize());
                    RoomDto room = roomService.toRoomDto(b.getBookingDetails().getRoom(), roomSize);
                    BookingDetailsDto detailsDto = bookingDetailsService.toBookingDetailsDto(b.getBookingDetails(), room);
                    CustomerDto customerDto = customerService.toCustomerDto(b.getCustomer());
                    return toBookingDto(b, detailsDto, customerDto);
                })
                .toList();
    }

    @Override
    public List<RoomDto> getAvailableRooms(RoomSearch roomSearch) {
        System.out.println(roomService.getRooms());
        return roomService.getRooms();
    }

}
