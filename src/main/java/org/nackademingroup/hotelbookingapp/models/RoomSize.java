package org.nackademingroup.hotelbookingapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSize {

    @Id @GeneratedValue
    private Long id;

    private int beds, maxExtraBeds; //max_extra_beds
    private String size;
}
