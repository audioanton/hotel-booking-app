package org.nackademingroup.hotelbookingapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    @Cascade(CascadeType.ALL)
    private Room room;

//    @ManyToOne
//    @JoinColumn
//    private Booking booking;

    private int extraBeds;
}
