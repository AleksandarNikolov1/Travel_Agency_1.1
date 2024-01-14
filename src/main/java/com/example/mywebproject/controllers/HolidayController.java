package com.example.mywebproject.controllers;

import com.example.mywebproject.dtos.holidayDtos.CreateHolidayDto;
import com.example.mywebproject.dtos.holidayDtos.UpdateHolidayDto;
import com.example.mywebproject.entities.Holiday;
import com.example.mywebproject.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public ResponseEntity<List<Holiday>> getAllHolidays(){
        return ResponseEntity.ok(this.holidayService.getAllHolidays());
    }

    @GetMapping("{id}")
    public ResponseEntity<Holiday> getHolidayById(@PathVariable Long id) {
        return holidayService.getHolidayById(id)
                .map(holiday -> new ResponseEntity<>(holiday, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Holiday> createHoliday(@RequestBody CreateHolidayDto createHolidayDTO) {
        Holiday holiday = this.holidayService.createHoliday(
                createHolidayDTO.getLocation(),
                createHolidayDTO.getTitle(),
                createHolidayDTO.getStartDate(),
                createHolidayDTO.getDuration(),
                createHolidayDTO.getPrice(),
                createHolidayDTO.getFreeSlots()).get();
        return ResponseEntity.ok(holiday);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        holidayService.deleteHoliday(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Holiday> updateHoliday(@RequestBody UpdateHolidayDto updateHolidayDTO) throws Exception {
        Holiday holiday = this.holidayService.updateHoliday(updateHolidayDTO.getId(),
                updateHolidayDTO.getLocation(),
                updateHolidayDTO.getTitle(),
                updateHolidayDTO.getStartDate(),
                updateHolidayDTO.getDuration(),
                updateHolidayDTO.getPrice(),
                updateHolidayDTO.getFreeSlots()).get();
        return ResponseEntity.ok().body(holiday);
    }
}


