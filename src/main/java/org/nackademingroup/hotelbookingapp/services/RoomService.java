package org.nackademingroup.hotelbookingapp.services;

import org.nackademingroup.hotelbookingapp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
}
