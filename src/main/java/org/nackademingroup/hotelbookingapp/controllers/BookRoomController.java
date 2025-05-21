package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.dto.RoomDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSearch;
import org.nackademingroup.hotelbookingapp.dto.RoomSelection;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookRoomController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/book-room")
    public String showBookRoom(Model model) {
        model.addAttribute(RoomSearch.builder().build());
        model.addAttribute("search", false);
        return "book-room";
    }

    @PostMapping("/book-room")
    public String showAvailableRooms(Model model, RoomSearch roomSearch) {
        model.addAttribute("rooms", bookingService.getAvailableRooms(roomSearch));
        model.addAttribute(roomSearch);
        model.addAttribute("guests", List.of());
        model.addAttribute("search", true);
        model.addAttribute("roomSelection", RoomSelection.builder().build());
        return "book-room";
    }

    @PostMapping("/book-room/selected")
    public String selectRoom(Model model, RoomSelection roomSelection) {
        return "index";
    }
}
