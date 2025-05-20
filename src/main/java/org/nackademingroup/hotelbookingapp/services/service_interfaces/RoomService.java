package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.RoomDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSizeDto;
import org.nackademingroup.hotelbookingapp.models.Room;

public interface RoomService {

    public RoomDto toRoomDto(Room room, RoomSizeDto roomSizeDto);
}
