package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Room;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    Room room = Room.builder().name("namn").build();
}
