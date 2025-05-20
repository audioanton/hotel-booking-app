package org.nackademingroup.hotelbookingapp;

import org.nackademingroup.hotelbookingapp.models.*;
import org.nackademingroup.hotelbookingapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HotelBookingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingAppApplication.class, args);

    }


    @Bean
    public CommandLineRunner startup(RoomRepository roomRepository, CustomerRepository customerRepository, RoomSizeRepository roomSizeRepository, BookingDetailsRepository bookingDetailsRepository, BookingRepository bookingRepository) {
        return args -> {
            List<RoomSize> roomSizes = List.of(
                    RoomSize.builder().size("Large").beds(4).maxExtraBeds(2).build()
            );
//            roomSizeRepository.saveAll(roomSizes);

            List<Room> rooms = List.of(
                    Room.builder().name("The suit").roomsize(roomSizes.get(0)).build(),
                    Room.builder().name("The scrub").roomsize(roomSizes.get(0)).build()
            );

//            roomRepository.saveAll(rooms);

            Customer c1 = Customer.builder().name("Antonio Larzon").build();
//            customerRepository.save(c1);

            List<BookingDetails> bookingDetails = List.of(
                    BookingDetails.builder().room(rooms.get(0)).extraBeds(2).build()
            );
//            bookingDetailsRepository.saveAll(bookingDetails);

            List<Booking> bookings = List.of(
                    Booking.builder()
                            .startDate(LocalDate.parse("2025-05-20"))
                            .endDate(LocalDate.parse("2025-05-25"))
                            .customer(c1)
                            .details(bookingDetails.get(0))
                            .build()
            );

            bookingRepository.saveAll(bookings);
        };
    }

}
