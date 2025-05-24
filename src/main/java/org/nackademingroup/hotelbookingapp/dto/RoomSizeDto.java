package org.nackademingroup.hotelbookingapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSizeDto {
    @NotNull(message = "Room size is required")
    @Size(min = 1, max = 30, message = "Room size name must be between 1 and 30 characters")
    private String size;

    @Min(value = 1, message = "Room must have at least 1 bed")
    private int beds;

    @Min(value = 0, message = "Maximum extra beds cannot be negative")
    private int maxExtraBeds;
}
