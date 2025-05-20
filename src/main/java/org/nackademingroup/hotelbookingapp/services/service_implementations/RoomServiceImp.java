package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.RoomDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSizeDto;
import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.repositories.RoomRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImp implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDto toRoomDto(Room room, RoomSizeDto roomSizeDto) {
        return RoomDto.builder().name(room.getName()).roomSize(roomSizeDto).build();
    }
}
