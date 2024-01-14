package com.example.mywebproject.services;

import com.example.mywebproject.dtos.holidayDtos.CreateHolidayDto;
import com.example.mywebproject.dtos.holidayDtos.UpdateHolidayDto;
import com.example.mywebproject.entities.Holiday;
import com.example.mywebproject.entities.Location;
import com.example.mywebproject.repositories.HolidayRepository;
import com.example.mywebproject.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository, LocationRepository locationRepository, ModelMapper modelMapper) {
        this.holidayRepository = holidayRepository;
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Holiday> createHoliday(String locationStr, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Location location = locationRepository.findById(Long.valueOf(locationStr)).orElseThrow(RuntimeException::new);

        Holiday holiday = new Holiday(location, title, startDate, duration, price, freeSlots);
        holidayRepository.save(holiday);

        return Optional.of(holiday);
    }

    public Optional<Holiday> getHolidayById(Long id) {
        return holidayRepository.findById(id);
    }

    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Transactional
    public Optional<Holiday> updateHoliday(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) throws Exception  {
        Location existingLocation = locationRepository.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Holiday holiday = this.holidayRepository.findById(id).get();
        holiday.setLocation(existingLocation);
        holiday.setTitle(title);
        holiday.setStartDate(startDate);
        holiday.setDuration(duration);
        holiday.setPrice(price);
        holiday.setFreeSlots(freeSlots);

        this.holidayRepository.save(holiday);
        return Optional.of(holiday);
    }

    public void deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
    }

}
