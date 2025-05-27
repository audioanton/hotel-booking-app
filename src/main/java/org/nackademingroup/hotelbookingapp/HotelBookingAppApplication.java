package org.nackademingroup.hotelbookingapp;

import org.nackademingroup.hotelbookingapp.models.*;
import org.nackademingroup.hotelbookingapp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HotelBookingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingAppApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner startup(RoomRepository roomRepository, CustomerRepository customerRepository, RoomSizeRepository roomSizeRepository, BookingDetailsRepository bookingDetailsRepository, BookingRepository bookingRepository) {
        return args -> {
            bookingRepository.deleteAll();
            customerRepository.deleteAll();
            roomRepository.deleteAll();
            roomSizeRepository.deleteAll();

            List<RoomSize> roomSizes = List.of(
                    RoomSize.builder().size("Small").beds(1).maxExtraBeds(0).build(),
                    RoomSize.builder().size("Medium").beds(2).maxExtraBeds(1).build(),
                    RoomSize.builder().size("Large").beds(2).maxExtraBeds(2).build()
            );
            roomSizeRepository.saveAll(roomSizes);

            List<Room> rooms = List.of(
                    Room.builder().name("The scrub").roomsize(roomSizes.get(0)).build(),
                    Room.builder().name("The standard").roomsize(roomSizes.get(1)).build(),
                    Room.builder().name("The suite").roomsize(roomSizes.get(2)).build()
            );
            roomRepository.saveAll(rooms);

            List<Customer> customers = List.of(
                    Customer.builder().name("Marcuso Efternamno?").build(),
                    Customer.builder().name("Antonio Larzon").build(),
                    Customer.builder().name("Vittorio Jonassono").build()
            );
            customerRepository.saveAll(customers);

            List<BookingDetails> bookingDetails = List.of(
                    BookingDetails.builder().room(rooms.get(0)).extraBeds(0).build(),
                    BookingDetails.builder().room(rooms.get(1)).extraBeds(1).build(),
                    BookingDetails.builder().room(rooms.get(2)).extraBeds(2).build()
            );

            List<Booking> bookings = List.of(
                    Booking.builder()
                            .startDate(LocalDate.parse("2025-05-30"))
                            .endDate(LocalDate.parse("2025-05-31"))
                            .customer(customers.get(0))
                            .bookingDetails(bookingDetails.get(0))
                            .build()
            );
            bookingRepository.saveAll(bookings);
        };
    }
}