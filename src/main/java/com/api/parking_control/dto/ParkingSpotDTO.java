package com.api.parking_control.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParkingSpotDTO
        (@NotBlank String parkingSpotNumber,
        @NotBlank @Size(max = 7) String licencePlateCar,
        @NotBlank String brandCar,
        @NotBlank String modelCar,
        @NotBlank String colorCar,
        @NotBlank String responsibleName,
        @NotBlank String apartment,
        @NotBlank String block)
{
}
