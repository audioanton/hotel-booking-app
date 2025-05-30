package org.nackademingroup.hotelbookingapp.controllers;

import jakarta.validation.Valid;
import org.nackademingroup.hotelbookingapp.dto.BookingDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSearchDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSelectionDto;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookRoomController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    CustomerService customerService;

    @GetMapping("/book-room")
    public String showBookRoom(Model model) {
        model.addAttribute(RoomSearchDto.builder().build());
        model.addAttribute("errorMessage", "");
        model.addAttribute("search", false);
        return "book-room";
    }

    @PostMapping("/book-room")
    public String showAvailableRooms(Model model, @Valid  RoomSearchDto roomSearchDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Please fill in all required fields.");
            model.addAttribute("search", false);
            return "book-room";
        }
        try {
            model.addAttribute("rooms", bookingService.getAvailableRooms(roomSearchDto));
            model.addAttribute("errorMessage", "");
            model.addAttribute(roomSearchDto);
            model.addAttribute("customers", customerService.getCustomerDtos());
            model.addAttribute("search", true);
            model.addAttribute("roomSearchDto", roomSearchDto);
            model.addAttribute("roomSelectionDto", new RoomSelectionDto());
            return "book-room";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "book-room";
        }
    }

    @PostMapping("/book-room/selected")
    public String selectRoom(Model model, @Valid RoomSelectionDto roomSelectionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(RoomSearchDto.builder().build());
            model.addAttribute("errorMessage", "Valid booking must have a selected room.");
            model.addAttribute("search", true);
            return "book-room";
        }
        model.addAttribute("errorMessage", "");
        bookingService.createBooking(roomSelectionDto);
        return "redirect:/bookings";
    }
}
