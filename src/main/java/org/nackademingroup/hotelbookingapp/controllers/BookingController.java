package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public String booking(Model model) {
        List<Booking> bookings = bookingService.getMockBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/bookings/{id}")
    public String getBooking(@PathVariable("id") Long id, Model model) {
        Optional<Booking> bookingOpt = bookingService.getBookingById(id);

        if (bookingOpt.isPresent()) {
            model.addAttribute("booking", bookingOpt.get());
            return "booking";
        } else {
            return "redirect:/bookings";
        }
    }
}