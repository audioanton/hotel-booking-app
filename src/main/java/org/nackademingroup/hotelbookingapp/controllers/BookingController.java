package org.nackademingroup.hotelbookingapp.controllers;

import jakarta.validation.Valid;
import org.nackademingroup.hotelbookingapp.dto.BookingDto;
import org.nackademingroup.hotelbookingapp.models.Booking;
import org.nackademingroup.hotelbookingapp.services.service_implementations.BookingServiceImp;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String editExtraBeds(@PathVariable("id") Long id, @Valid Booking booking, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/bookings/" + id;
        }

        try {
            bookingService.updateBookingExtraBeds(id, booking);
            redirectAttributes.addFlashAttribute("successMessageExtraBeds", "Extra beds updated successfully");
            return "redirect:/bookings/" + id;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessageExtraBeds", e.getMessage());
            return "redirect:/bookings/" + id;
        }
    }

    @PostMapping("/bookings/{id}/edit/dates")
    public String editDates(@PathVariable("id") Long id, @Valid BookingDto bookingDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/bookings/" + id;
        }

        try {
            bookingService.updateBookingDates(id, bookingDto);
            redirectAttributes.addFlashAttribute("successMessageDates", "Dates updated successfully");
            return "redirect:/bookings/" + id;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessageDates", e.getMessage());
            return "redirect:/bookings/" + id;
        }
    }

}

