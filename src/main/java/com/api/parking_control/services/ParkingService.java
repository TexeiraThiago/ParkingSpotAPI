package com.api.parking_control.services;

import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.repository.ParkingSpotRepository;
import com.api.parking_control.services.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingSpotRepository repository;

    public ParkingService(ParkingSpotRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public ParkingSpotModel save (ParkingSpotModel model) {
        return  repository.save(model);
    }

    public boolean existByLicensePlateCar(String licensePlate) {
        return repository.existsByLicencePlateCar(licensePlate);
    }

    public boolean existsByParkingSpot(String number) {
        return repository.existsByNumber(number);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return repository.existsByApartmentAndBlock(apartment, block);
    }


    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ParkingSpotModel findById(UUID id) {
        Optional<ParkingSpotModel> spotModelOptional = repository.findById(id);
        if (spotModelOptional.isEmpty()) {
            throw new NotFoundException();
        }
        return spotModelOptional.get();
    }

    @Transactional
    public void deleteParkingSpot(UUID id) {
        repository.delete(findById(id));
    }
}
