package org.nackademingroup.hotelbookingapp.services;

import org.nackademingroup.hotelbookingapp.repositories.BookingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsService {

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;
}
