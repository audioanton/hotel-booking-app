package org.nackademingroup.hotelbookingapp.repositories;

import org.nackademingroup.hotelbookingapp.models.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
}
