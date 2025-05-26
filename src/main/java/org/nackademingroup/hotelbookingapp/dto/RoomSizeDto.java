package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSizeDto {
    @NotBlank(message = "Room size is required")
    private String size;
    
    @Min(value = 1, message = "Number of beds must be at least 1")
    private int beds;
    
    @Min(value = 0, message = "Max extra beds cannot be negative")
    private int maxExtraBeds;
}
