package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.RoomSizeDto;
import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.models.RoomSize;
import org.nackademingroup.hotelbookingapp.repositories.RoomSizeRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.RoomSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSizeServiceImp implements RoomSizeService {
    @Autowired
    RoomSizeRepository roomSizeRepository;


    @Override
    public RoomSize getRoomSizeById(Long id) {
        return roomSizeRepository.findById(id).orElse(null);
    }

    @Override
    public RoomSizeDto toRoomSizeDto(RoomSize roomSize) {
        return RoomSizeDto.builder().size(roomSize.getSize()).build();
    }
}
