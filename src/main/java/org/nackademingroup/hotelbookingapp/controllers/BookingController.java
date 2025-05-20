package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.dto.BookingDto;
import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.services.service_implementations.BookingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingServiceImp bookingService;

    @GetMapping("/bookings")
    public String booking(Model model) {
        List<BookingDto> bookings = bookingService.getBookings();
//        List<BookingDto> bookings = bookingService.getMockBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/bookings/{id}")
    public String getBooking(@PathVariable("id") Long id, Model model) {
        Optional<BookingDto> bookingOpt = bookingService.getBookingById(id);

        if (bookingOpt.isPresent()) {
            model.addAttribute("booking", bookingOpt.get());
            return "booking";
        } else {
            return "redirect:/bookings";
        }
    }
}