package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.*;
import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.models.BookingDetails;
import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.repositories.BookingRepository;
import org.nackademingroup.hotelbookingapp.repositories.RoomRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImp implements org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService {

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
//    @Autowired
//    private BookingService bookingService;
    @Autowired
    private RoomRepository roomRepository;

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

    //ToDo: Break duplicate code into function (the mapping of a booking to a bookingDto)

    public BookingDto getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);

        return booking.map(b -> {
            RoomSizeDto roomSize = roomSizeService.toRoomSizeDto(b.getBookingDetails().getRoom().getRoomsize());
            RoomDto room = roomService.toRoomDto(b.getBookingDetails().getRoom(), roomSize);
            BookingDetailsDto detailsDto = bookingDetailsService.toBookingDetailsDto(b.getBookingDetails(), room);
            CustomerDto customerDto = customerService.toCustomerDto(b.getCustomer());
            return toBookingDto(b, detailsDto, customerDto);
        }).orElse(null);
    }

    public Booking updateBooking(Booking booking) {
        return booking;
    }

    @Override
    public void removeBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void updateBookingExtraBeds(Long id, BookingDto updatedBooking) {
        bookingRepository.findById(id).ifPresent(b -> {
            BookingDetails currentBooking = b.getBookingDetails();
            int maxExtraBeds = currentBooking.getRoom().getRoomsize().getMaxExtraBeds();

            if (updatedBooking.getBookingDetails().getExtraBeds() < 0) {
                throw new IllegalArgumentException("Extra beds cannot be negative");
            }
            if (updatedBooking.getBookingDetails().getExtraBeds() >= maxExtraBeds) {
                throw new IllegalArgumentException("Extra beds cannot exceed the maximum allowed (" + maxExtraBeds + ")");
            }
            currentBooking.setExtraBeds(updatedBooking.getBookingDetails().getExtraBeds());
            bookingRepository.save(b);
        });
    }

    @Override
    public void updateBookingDates(Long id, BookingDto updatedBooking) {
        bookingRepository.findById(id)
                .map(booking -> {
                    validateDates(updatedBooking.getStartDate(), updatedBooking.getEndDate());
                    validateAvailability(id, updatedBooking.getStartDate(), updatedBooking.getEndDate(), booking.getBookingDetails().getRoom().getId());
                    booking.setStartDate(updatedBooking.getStartDate());
                    booking.setEndDate(updatedBooking.getEndDate());
                    return bookingRepository.save(booking);
                })
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null)
            throw new IllegalArgumentException("Please enter both start and end date");
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
    }

    private void validateAvailability(Long id, LocalDate startDate, LocalDate endDate, Long roomId) {
        List<Booking> activeBookings = bookingRepository
                .findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        endDate.minusDays(1),
                        startDate.plusDays(1))
                .stream()
                .filter(booking -> !Objects.equals(booking.getId(), id))
                .toList();
        boolean isBooked = activeBookings.stream()
                .anyMatch(booking -> Objects.equals(booking.getBookingDetails()
                        .getRoom()
                        .getId(), roomId));
        if (isBooked) {
            throw new IllegalArgumentException("Room is not available for the selected dates");
        }
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
    public List<RoomDto> getAvailableRooms(RoomSearchDto roomSearchDto) {
        validateDates(roomSearchDto.getStartDate(), roomSearchDto.getEndDate());
        System.out.println(roomSearchDto);
        System.out.println(bookingRepository.findAllByEndDateBeforeAndStartDateAfter(roomSearchDto.getStartDate(), roomSearchDto.getEndDate()));
        List<Booking> activeBookings = bookingRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(
                roomSearchDto.getEndDate().minusDays(1),
                roomSearchDto.getStartDate().plusDays(1)
        );
        List<RoomDto> rooms = roomService.getRooms();

        List<RoomDto> availableRooms = new ArrayList<>();
        for (RoomDto room : rooms) {
            boolean isBooked = activeBookings.stream()
                    .anyMatch(booking -> Objects.equals(booking.getBookingDetails().getRoom().getId(), room.getId()));
            int maxBeds = room.getRoomSize().getBeds() + room.getRoomSize().getMaxExtraBeds();
            boolean hasEnoughBeds = roomSearchDto.getTotalGuests() <= maxBeds;
            if (!isBooked && hasEnoughBeds) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @Override
    public void createBooking(RoomSelectionDto roomSelectionDto) {
        Booking booking = new Booking();
        booking.setStartDate(roomSelectionDto.getStartDate());
        booking.setEndDate(roomSelectionDto.getEndDate());
        Optional<CustomerDto> customerOpt = customerService.getCustomerDtoById(roomSelectionDto.getCustomerId());
        customerOpt.ifPresent(customerDto -> booking.setCustomer(customerService.toCustomer(customerDto)));

        BookingDetails bookingDetails = new BookingDetails();
        Room room = roomRepository.findById(roomSelectionDto.getRoomId()).orElse(null);
        if (room != null) {
            bookingDetails.setRoom(room);
            bookingDetails.setExtraBeds(getExtraBedsForBooking(room.getRoomsize().getBeds(), roomSelectionDto));
            booking.setBookingDetails(bookingDetails);
            bookingRepository.save(booking);
        }
    }

    public int getExtraBedsForBooking(int beds, RoomSelectionDto roomSelectionDto) {
        return Math.max(roomSelectionDto.getTotalGuests() - beds, 0);
    }

    @Override
    public boolean hasActiveBookings(Long id) {
        return bookingRepository.countByCustomerId(id) > 0;
    }

}
