package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
