package org.nackademingroup.hotelbookingapp.repositories;

import org.nackademingroup.hotelbookingapp.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
