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
//            List<RoomSize> roomSizes = List.of(
//                    RoomSize.builder().size("Large").beds(4).maxExtraBeds(2).build()
//            );
//
//            List<Room> rooms = List.of(
//                    Room.builder().name("The suit").roomsize(roomSizes.get(0)).build(),
//                    Room.builder().name("The scrub").roomsize(roomSizes.get(0)).build()
//            );
//
            customerRepository.deleteAll();
            List<Customer> customers = List.of(
                    Customer.builder().name("Marcuso Efternamno?").build(),
                    Customer.builder().name("Antonio Larzon").build(),
                    Customer.builder().name("Vittorio Jonassono").build()
            );
            customerRepository.saveAll(customers);
//
//
//            List<BookingDetails> bookingDetails = List.of(
//                    BookingDetails.builder().room(rooms.get(0)).extraBeds(2).build(),
//                    BookingDetails.builder().room(rooms.get(1)).extraBeds(1).build(),
//                    BookingDetails.builder().room(rooms.get(0)).extraBeds(2).build()
//            );
//
//            List<Booking> bookings = List.of(
//                    Booking.builder()
//                            .startDate(LocalDate.parse("2025-05-20"))
//                            .endDate(LocalDate.parse("2025-05-25"))
//                            .customer(customers.get(0))
//                            .bookingDetails(bookingDetails.get(0))
//                            .build(),
//                    Booking.builder()
//                            .startDate(LocalDate.parse("2025-05-26"))
//                            .endDate(LocalDate.parse("2025-05-27"))
//                            .customer(customers.get(1))
//                            .bookingDetails(bookingDetails.get(1))
//                            .build(),
//                    Booking.builder()
//                            .startDate(LocalDate.parse("2025-06-01"))
//                            .endDate(LocalDate.parse("2025-06-05"))
//                            .customer(customers.get(2))
//                            .bookingDetails(bookingDetails.get(2))
//                            .build()
//            );
//
//            bookingRepository.saveAll(bookings);
        };
    }

}
