package org.nackademingroup.hotelbookingapp.repositories;

import org.nackademingroup.hotelbookingapp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
