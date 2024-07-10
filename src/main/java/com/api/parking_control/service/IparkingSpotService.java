package com.api.parking_control.service;

import com.api.parking_control.model.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface IparkingSpotService {

    ParkingSpot save(ParkingSpot parkingSpotModel);
    boolean existByLicencePlateCar(String licencePlateCar);
    boolean existByParkSpotNumber(String parkingSpotNumber);
    boolean existByApartmentAndBlock(String apartment, String block);
    Page<ParkingSpot> findAll(Pageable pageable);
    public Optional<ParkingSpot> findById(UUID id);
    public void delete(ParkingSpot parkingSpot);

}
