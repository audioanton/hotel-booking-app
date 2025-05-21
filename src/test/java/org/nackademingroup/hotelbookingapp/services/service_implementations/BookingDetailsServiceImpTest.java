package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.models.BookingDetails;
import org.nackademingroup.hotelbookingapp.repositories.BookingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookingDetailsServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    BookingDetailsServiceImp bookingDetailsServiceImp;
    @Autowired
    BookingDetailsRepository bookingDetailsRepository;

    @BeforeEach
    public void setup() {
        bookingDetailsRepository.deleteAll();
        bookingDetailsRepository.saveAll(
                List.of(
                        BookingDetails.builder().id(1L).extraBeds(0).build(),
                        BookingDetails.builder().id(2L).extraBeds(1).build(),
                        BookingDetails.builder().id(3L).extraBeds(2).build()
                )
        );
    }

    @Test
    void toBookingDetailsDto() {
        fail();
    }
}