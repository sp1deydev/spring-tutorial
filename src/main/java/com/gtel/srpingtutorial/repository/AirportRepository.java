package com.gtel.srpingtutorial.repository;

import com.gtel.srpingtutorial.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    boolean existsByIata(String iata);
}
