package org.nackademingroup.hotelbookingapp.repositories;

import org.nackademingroup.hotelbookingapp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    long countByCustomerId(Long customerId);

//    List<Booking> findAllByEndDateBeforeAndStartDateAfter(
//            LocalDate startDate,
//            LocalDate endDate);

    List<Booking> findAllByEndDateBeforeAndStartDateAfter(LocalDate startDate, LocalDate endDate);

//    List<Booking> findAllByBookingPeriodIsNotContaining(Period bookingPeriod);
}
