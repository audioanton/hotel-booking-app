package org.nackademingroup.hotelbookingapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoPeriod;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn
//    @Cascade(CascadeType.ALL)
    private Customer customer;

    @OneToOne
    @JoinColumn
    @Cascade(CascadeType.ALL)
    private BookingDetails bookingDetails;


}
