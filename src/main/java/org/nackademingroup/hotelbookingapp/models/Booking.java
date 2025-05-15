package org.nackademingroup.hotelbookingapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate start_date;

    @Temporal(TemporalType.DATE)
    private LocalDate end_date;


}
