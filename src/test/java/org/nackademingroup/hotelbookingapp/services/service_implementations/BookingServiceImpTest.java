package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.dto.RoomSelectionDto;
import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookingServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookingServiceImp bookingService;
    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    public void setup() {
        bookingRepository.deleteAll();
        bookingRepository.saveAll(
                List.of(
                        Booking.builder()
                                .startDate(LocalDate.of(2020,1,1))
                                .endDate(LocalDate.of(2020,1,2))
                                .build(),
                        Booking.builder()
                                .startDate(LocalDate.of(2024,1,1))
                                .endDate(LocalDate.of(2024,1,5))
                                .build(),
                        Booking.builder()
                                .startDate(LocalDate.of(2025,1,1))
                                .endDate(LocalDate.of(2025,1,3))
                                .build()
                )
        );
    }

    @Test
    void getBookingById() {
        fail();
    }

    @Test
    void updateBooking() {
        fail();
    }

    @Test
    void removeBooking() {
        fail();
    }

    @Test
    void toBookingDto() {
        fail();
    }

    @Test
    void getBookings() {
        fail();
    }

    @Test
    void getExtraDatesForBooking() {
        RoomSelectionDto noExtraBeds = RoomSelectionDto.builder().totalGuests(2).build();
        RoomSelectionDto oneExtraBed = RoomSelectionDto.builder().totalGuests(3).build();
        RoomSelectionDto twoExtraBeds = RoomSelectionDto.builder().totalGuests(4).build();

        assertEquals(0, bookingService.getExtraBedsForBooking(2, noExtraBeds));
        assertEquals(1, bookingService.getExtraBedsForBooking(2, oneExtraBed));
        assertEquals(2, bookingService.getExtraBedsForBooking(2, twoExtraBeds));
    }
}