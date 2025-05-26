package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.dto.BookingDto;
import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.services.service_implementations.BookingServiceImp;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public String booking(Model model) {
        List<BookingDto> bookings = bookingService.getBookings();
//        List<BookingDto> bookings = bookingService.getMockBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/bookings/{id}")
    public String getBooking(@PathVariable("id") Long id, Model model) {
        BookingDto booking = bookingService.getBookingById(id);

        if (booking == null) {
            return "redirect:/bookings";
        } else {
            model.addAttribute("booking", booking);
            model.addAttribute("search", false);
            return "booking";
        }
    }

    @PostMapping("/bookings/{id}/cancel")
    public String cancelBooking(@PathVariable("id") Long id) {
        bookingService.removeBooking(id);
        return "redirect:/bookings";
    }

    @PostMapping("/bookings/{id}/edit/extra-beds")
    public String editExtraBeds(@PathVariable("id") Long id, BookingDto bookingDto, Model model) {
        try {
            bookingService.updateBookingExtraBeds(id, bookingDto);
            BookingDto updatedBooking = bookingService.getBookingById(id);
            model.addAttribute("booking", updatedBooking);
            model.addAttribute("successMessageExtraBeds", "Extra beds updated successfully");
            return "booking";
        } catch (IllegalArgumentException e) {
            BookingDto currentBooking = bookingService.getBookingById(id);
            model.addAttribute("booking", currentBooking);
            model.addAttribute("errorMessageExtraBeds", e.getMessage());
            return "booking";
        }
    }

    @PostMapping("/bookings/{id}/edit/dates")
    public String editDates(@PathVariable("id") Long id, BookingDto bookingDto, Model model) {
        try {
            bookingService.updateBookingDates(id, bookingDto);
            BookingDto updatedBooking = bookingService.getBookingById(id);
            model.addAttribute("booking", updatedBooking);
            model.addAttribute("successMessageDates", "Dates updated successfully");
            return "booking";
        } catch (IllegalArgumentException e) {
            BookingDto currentBooking = bookingService.getBookingById(id);
            model.addAttribute("booking", currentBooking);
            model.addAttribute("errorMessageDates", e.getMessage());
            return "booking";
        }
    }

}