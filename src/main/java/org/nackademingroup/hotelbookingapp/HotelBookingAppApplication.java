package org.nackademingroup.hotelbookingapp;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.nackademingroup.hotelbookingapp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HotelBookingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingAppApplication.class, args);

    }


    @Bean
    public CommandLineRunner startup(RoomRepository roomRepository, CustomerRepository customerRepository) {
        return args -> {

            List<Room> rooms = List.of(
                    Room.builder().name("The suit").build(),
                    Room.builder().name("The scrub").build()
            );

            roomRepository.saveAll(rooms);

            Customer c1 = Customer.builder().name("Antonio Larzon").build();
            customerRepository.save(c1);
        };
    }

}
