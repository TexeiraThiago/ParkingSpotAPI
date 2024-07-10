package com.api.parking_control.controller;

import com.api.parking_control.dto.ParkingSpotDTO;
import com.api.parking_control.model.ParkingSpot;
import com.api.parking_control.service.IparkingSpotService;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    IparkingSpotService parkingSpotService;
    @Autowired
    ParkingSpot parkingSpotModel;

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot (@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        if (parkingSpotService.existByLicencePlateCar(parkingSpotDTO.licencePlateCar())) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Licence Plate Car already in use!");
        }
        if (parkingSpotService.existByParkSpotNumber(parkingSpotDTO.parkingSpotNumber())) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already in use!");
        }

        if (parkingSpotService.existByApartmentAndBlock(parkingSpotDTO.apartment(), parkingSpotDTO.block())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered");
        }
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpot>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpot>  parkingSoptOptional = parkingSpotService.findById(id);
        if (parkingSoptOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(parkingSoptOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpotService.findById(id);
        if (parkingSpotOptional.isPresent()) {
            parkingSpotService.delete(parkingSpotOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not Found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> udpateParkingSpot(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ParkingSpotDTO parkingSpotDTO
    ) {
        Optional<ParkingSpot> parkingSpotOptional =parkingSpotService.findById( id);

        if (parkingSpotOptional.isPresent()) {
            BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
            parkingSpotModel.setId(parkingSpotOptional.get().getId());
            parkingSpotModel.setRegistrationDate(parkingSpotOptional.get().getRegistrationDate());
            return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not Found");
    }
}
