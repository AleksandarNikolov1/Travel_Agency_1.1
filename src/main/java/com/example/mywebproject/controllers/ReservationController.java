package com.example.mywebproject.controllers;

import com.example.mywebproject.dtos.reservationDtos.CreateReservationDto;
import com.example.mywebproject.dtos.reservationDtos.ResponseReservationDto;
import com.example.mywebproject.dtos.reservationDtos.UpdateReservationDto;
import com.example.mywebproject.entities.Reservation;
import com.example.mywebproject.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(this.reservationService.getAllReservations());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return this.reservationService.getReservationById(id)
                .map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-reservation")
    public ResponseEntity<List<Reservation>> findAllByPhoneNum(@RequestBody ResponseReservationDto reservationDTO) {
        return ResponseEntity.ok(this.reservationService.reservationsByPhoneNumber(reservationDTO.getPhoneNumber()));
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> save(@RequestBody CreateReservationDto reservationDto) {
        Reservation reservation = this.reservationService.createReservation(reservationDto.getContactName(),
                reservationDto.getPhoneNumber(),
                reservationDto.getHoliday()).get();
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservations")
    public ResponseEntity<Reservation> edit(@RequestBody UpdateReservationDto reservationDto) throws Exception {
        Reservation reservation = this.reservationService.updateReservation(reservationDto.getId(),
                reservationDto.getContactName(),
                reservationDto.getPhoneNumber(),
                reservationDto.getHoliday()).get();
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Reservation> deleteById(@PathVariable Long id) {
        this.reservationService.deleteReservation(id);
        if (this.reservationService.getReservationById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
