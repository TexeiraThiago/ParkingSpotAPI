package com.api.parking_control.service;

import com.api.parking_control.model.ParkingSpot;
import com.api.parking_control.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public ParkingSpot save(ParkingSpot parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    //Método customizado precisa declarar no repository

    public boolean existByLicencePlateCar(String licencePlateCar) {
        return parkingSpotRepository.existByLicencePlateCar(licencePlateCar).isPresent();
    }

    public boolean existByParkSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existByParkSpotNumber(parkingSpotNumber).isPresent();
    }

    public boolean existByApartmentAndBlock(String apartment, String block) {
        return  parkingSpotRepository.existByApartmentAndBlock(apartment, block).isPresent();
    }
    //------------------------------------------------------------------------------------------------

    public Page<ParkingSpot> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpot> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpot parkingSpot) {
        parkingSpotRepository.delete(parkingSpot);
    }

}
