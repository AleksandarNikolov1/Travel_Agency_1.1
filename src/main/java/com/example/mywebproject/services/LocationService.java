package com.example.mywebproject.services;

import com.example.mywebproject.dtos.holidayDtos.UpdateHolidayDto;
import com.example.mywebproject.dtos.locationDtos.CreateLocationDto;
import com.example.mywebproject.dtos.locationDtos.UpdateLocationDto;
import com.example.mywebproject.entities.Holiday;
import com.example.mywebproject.entities.Location;
import com.example.mywebproject.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LocationService(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    public Location createLocation(String street, String number, String city, String country) {
        Location location = new Location(street, number, city, country);
        this.locationRepository.save(location);
        return location;
    }

    public Optional<Location> getLocationById(Long id) {
        return this.locationRepository.findById(id);
    }

    public List<Location> getAllLocations() {
        return this.locationRepository.findAll();
    }


    public Optional<Location> updateLocation(Long id, String street, String number, String city, String country) {
        Location location = this.locationRepository.findById(id).get();
        location.setStreet(street);
        location.setNumber(number);
        location.setCity(city);
        location.setCountry(country);

        this.locationRepository.save(location);
        return Optional.of(location);
    }

    public void deleteLocation(Long id) throws EntityNotFoundException {
        try {
            this.locationRepository.deleteById(id);
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException(String.format("Location with id - %d not found.", id));
        }
    }
}
