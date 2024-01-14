package com.example.mywebproject.repositories;

import com.example.mywebproject.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, Long>{
}
