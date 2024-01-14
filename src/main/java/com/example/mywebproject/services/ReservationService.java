package com.example.mywebproject.services;

import com.example.mywebproject.entities.Holiday;
import com.example.mywebproject.repositories.HolidayRepository;
import org.modelmapper.ModelMapper;
import com.example.mywebproject.dtos.reservationDtos.CreateReservationDto;
import com.example.mywebproject.dtos.reservationDtos.UpdateReservationDto;
import com.example.mywebproject.entities.Reservation;
import com.example.mywebproject.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HolidayRepository holidayRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, HolidayRepository holidayRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.holidayRepository = holidayRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Reservation> createReservation(String name, String number, String holidayStr) {
        Holiday holiday = this.holidayRepository.findById(Long.valueOf(holidayStr)).orElseThrow(RuntimeException::new);
        Reservation reservation = new Reservation(name, number, holiday);
        this.reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> updateReservation(Long id, String name, String number, String holidayStr) throws Exception {
        Holiday holiday = this.holidayRepository.findById(Long.valueOf(holidayStr)).orElseThrow(RuntimeException::new);
        Optional<Reservation> optReservation = this.reservationRepository.findById(id);

        Reservation reservation = optReservation.get();
        reservation.setContactName(name);
        reservation.setPhoneNumber(number);
        reservation.setHoliday(holiday);
        this.reservationRepository.save(reservation);
        return Optional.of(reservation);
    }


    public void deleteReservation(Long id) throws EntityNotFoundException {
        try {
            this.reservationRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(String.format("Reservation with id - %d not found.", id));
        }
    }

    public List<Reservation> reservationsByPhoneNumber(String phoneNumber){
        List<Reservation> reservations = new ArrayList<>();

        for (Reservation reservation : this.reservationRepository.findAll()) {
            if (reservation.getPhoneNumber().equals(phoneNumber))
                reservations.add(reservation);
        }

        return reservations;
    }
}
