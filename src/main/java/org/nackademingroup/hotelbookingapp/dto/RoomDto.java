package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @NotBlank(message = "Room name is required")
    private String name;
    
    @NotNull(message = "Room size is required")
    private RoomSizeDto roomSize;
}
