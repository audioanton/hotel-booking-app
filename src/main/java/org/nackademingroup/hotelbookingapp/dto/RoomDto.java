package org.nackademingroup.hotelbookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    private Long id;
    private String name;
    private RoomSizeDto roomSize;
}
