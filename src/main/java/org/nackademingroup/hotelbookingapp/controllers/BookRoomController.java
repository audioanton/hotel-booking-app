package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.dto.RoomSearchDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSelectionDto;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("search", false);
        return "book-room";
    }

    @PostMapping("/book-room")
    public String showAvailableRooms(Model model, RoomSearchDto roomSearchDto) {
        model.addAttribute("rooms", bookingService.getAvailableRooms(roomSearchDto));
        model.addAttribute(roomSearchDto);
        model.addAttribute("customers", customerService.getCustomerDtos());
        model.addAttribute("search", true);
        model.addAttribute("roomSearchDto", roomSearchDto);
        model.addAttribute("roomSelectionDto", new RoomSelectionDto());
        return "book-room";
    }

    @PostMapping("/book-room/selected")
    public String selectRoom(Model model, RoomSelectionDto roomSelectionDto) {
//        bookingService.saveRoomSelection(roomSelection);
        return "redirect:/book-room";
    }
}
