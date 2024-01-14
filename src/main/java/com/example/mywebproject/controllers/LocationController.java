package com.example.mywebproject.controllers;

import com.example.mywebproject.dtos.locationDtos.CreateLocationDto;
import com.example.mywebproject.dtos.locationDtos.ResponseLocationDto;
import com.example.mywebproject.dtos.locationDtos.UpdateLocationDto;
import com.example.mywebproject.entities.Location;
import com.example.mywebproject.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(this.locationService.getAllLocations());
    }

    @GetMapping("{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return this.locationService.getLocationById(id)
                .map(l -> ResponseEntity.ok().body(l))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody CreateLocationDto locationDto) {

        Location location = this.locationService.createLocation(
                locationDto.getStreet(),
                locationDto.getNumber(),
                locationDto.getCity(),
                locationDto.getCountry());

        return ResponseEntity.ok(location);
    }

    @PutMapping
    public ResponseEntity<Location> updateLocation(@RequestBody UpdateLocationDto locationDto) {

        Location location = this.locationService.updateLocation(
                locationDto.getId(),
                locationDto.getStreet(),
                locationDto.getNumber(),
                locationDto.getCity(),
                locationDto.getCountry()).get();

        return ResponseEntity.ok().body(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        this.locationService.deleteLocation(id);

        if (this.locationService.getLocationById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}