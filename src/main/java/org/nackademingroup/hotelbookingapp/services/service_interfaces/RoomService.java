package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.RoomDto;
import org.nackademingroup.hotelbookingapp.dto.RoomSizeDto;
import org.nackademingroup.hotelbookingapp.models.Room;

import java.util.List;

public interface RoomService {
    public List<RoomDto> getRooms();

    public RoomDto toRoomDto(Room room, RoomSizeDto roomSizeDto);
}
