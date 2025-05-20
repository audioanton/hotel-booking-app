package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.RoomSizeDto;
import org.nackademingroup.hotelbookingapp.models.Room;
import org.nackademingroup.hotelbookingapp.models.RoomSize;

public interface RoomSizeService {
    public RoomSize getRoomSizeById(Long id);

    public RoomSizeDto toRoomSizeDto(RoomSize roomSize);
}
