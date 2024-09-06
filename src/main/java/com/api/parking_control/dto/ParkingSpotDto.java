package com.api.parking_control.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParkingSpotDto(
        @NotBlank String number,
        @NotBlank @Size(max = 7) String licencePlateCar,
        @NotBlank String brand,
        @NotBlank String model,
        @NotBlank String color,
        @NotBlank String responsibleName,
        @NotBlank String apartment,
        @NotBlank String block
) {

}
