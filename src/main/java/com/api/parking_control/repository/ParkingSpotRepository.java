package com.api.parking_control.repository;

import com.api.parking_control.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository <ParkingSpot, UUID> {

    @Query("SELECT ps FROM ParkingSpot ps WHERE ps.licencePlateCar = :licencePlateCar")
    Optional<ParkingSpot> existByLicencePlateCar(@Param("licencePlateCar") String licencePlateCar);

    @Query(value = "SELECT ps FROM ParkingSpot ps WHERE ps.parkingSpotNumber = :parkingSpotNumber")
    Optional<ParkingSpot> existByParkSpotNumber(@Param("parkingSpotNumber") String parkingSpotNumber);

    @Query(value = "SELECT ps FROM ParkingSpot ps WHERE ps.apartment = :apartment AND ps.block = :block")
    Optional<ParkingSpot> existByApartmentAndBlock(@Param("apartment") String apartment, @Param("block") String block);

}


