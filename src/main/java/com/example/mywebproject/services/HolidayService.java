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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository, LocationRepository locationRepository) {
        this.holidayRepository = holidayRepository;
        this.locationRepository = locationRepository;
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

    public List<Holiday> findHolidayByCriteria (String location, LocalDate startDate, Integer duration){
        List<Holiday> holidays = new ArrayList<>();

        if (location == null && startDate == null){
            for (Holiday holiday : this.holidayRepository.findAll()) {
                if (Objects.equals(holiday.getDuration(), duration)){
                    holidays.add(holiday);
                }
            }
        }
        else if (location == null && duration == null){
            for (Holiday holiday : this.holidayRepository.findAll()) {
                if (Objects.equals(holiday.getStartDate(), startDate)){
                    holidays.add(holiday);
                }
            }
        }

        else if (startDate == null && duration == null){
            for (Holiday holiday : this.holidayRepository.findAll()) {
                if (holiday.getLocation().getCity().equals(location) || holiday.getLocation().getCountry().equals(location)){
                    holidays.add(holiday);
                }
            }
        }
        else if (location == null){
            for (Holiday holiday : this.holidayRepository.findAll()) {
                if (holiday.getStartDate() == startDate && Objects.equals(holiday.getDuration(), duration)){
                    holidays.add(holiday);
                }
            }
        }
        else if (startDate == null){
            for (Holiday holiday : this.holidayRepository.findAll()) {
                if ((holiday.getLocation().getCity().equals(location) || holiday.getLocation().getCountry().equals(location)) && Objects.equals(holiday.getDuration(), duration)){
                    holidays.add(holiday);
                }
            }
        }
        else if (duration == null){
            for (Holiday holiday : this.holidayRepository.findAll()) {
                if ((holiday.getLocation().getCity().equals(location) || holiday.getLocation().getCountry().equals(location)) && holiday.getStartDate() == startDate){
                    holidays.add(holiday);
                }
            }
        }

        return holidays;

    }

}
