package com.api.parking_control.repository;

import com.api.parking_control.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    boolean existsByLicencePlateCar(String licensePlateCar);
    boolean existsByNumber(String number);
    boolean existsByApartmentAndBlock(String apartment, String block);

}
