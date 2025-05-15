package org.nackademingroup.hotelbookingapp.services;

import org.nackademingroup.hotelbookingapp.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
}
