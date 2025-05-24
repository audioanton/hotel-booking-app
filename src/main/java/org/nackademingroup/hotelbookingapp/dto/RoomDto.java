package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Room must have a name")
    @Size(min = 1, max = 50, message = "Room name must be between 1 and 50 characters")
    private String name;
    @NotNull(message = "Room must have a room size")
    private RoomSizeDto roomSize;
}
