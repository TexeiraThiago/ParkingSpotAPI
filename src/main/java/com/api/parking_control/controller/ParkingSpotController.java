package com.api.parking_control.controller;

import com.api.parking_control.dto.ParkingSpotDto;
import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.services.ParkingService;
import com.api.parking_control.services.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    private final ParkingService service;

    public ParkingSpotController(ParkingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ParkingSpotModel> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        if (service.existByLicensePlateCar(parkingSpotDto.licencePlateCar())) {
            throw new BusinessException("Conflict: Licence Plate Car is already in user");
        }
        if (service.existsByParkingSpot(parkingSpotDto.number())) {
            throw new BusinessException("Conflict: ParkingSpot is already in user");
        }
        if(service.existsByApartmentAndBlock(parkingSpotDto.apartment(), parkingSpotDto.block())) {
            throw new BusinessException("Conflict: Parking spot already registered for apartment/block");
        }
        ParkingSpotModel model = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, model);
        model.setRegistrationData(LocalDateTime.now(ZoneId.of("UTC")));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(model.getId()).toUri();
        return ResponseEntity.created(uri).body(service.save(model));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(
            @PageableDefault(page = 0, sort = "id", size = 10,direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ParkingSpotModel> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        ParkingSpotModel model = service.findById(id);
        return ResponseEntity.ok().body(model);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteOneParKingSpot(@PathVariable(value = "id")UUID id) {
        service.deleteParkingSpot(id);
        return ResponseEntity.ok().body("Parking Spot deleted successfully");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ParkingSpotModel> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        ParkingSpotModel model = service.findById(id);
        ParkingSpotModel newModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, newModel);
        newModel.setId(model.getId());
        newModel.setRegistrationData(model.getRegistrationData());
        return ResponseEntity.ok().body(service.save(newModel));
    }
}
