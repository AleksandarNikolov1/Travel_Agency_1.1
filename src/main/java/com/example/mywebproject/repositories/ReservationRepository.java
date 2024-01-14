package com.example.mywebproject.repositories;

import com.example.mywebproject.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository <Reservation, Long>{
}
