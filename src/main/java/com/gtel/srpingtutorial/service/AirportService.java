package com.gtel.srpingtutorial.service;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.model.Airport;
import com.gtel.srpingtutorial.repository.AirportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirportService {
    AirportRepository airportRepository;

    public Page<Airport> getAirports(int page, int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return airportRepository.findAll(pageable);
//        return airportRepository.findAll(pageable).getContent();
    }

    public int countAirports() {
        return (int) airportRepository.count();
    }

    public Airport getAirport(String iata) {
        return airportRepository.findById(iata).orElseThrow(() -> new RuntimeException("User Not Found!"));
    }

    public Airport createAirport(AirportRequest airportRequest) {
        if(airportRepository.existsByIata(airportRequest.getIata())) {
            throw new RuntimeException("Airport is existed!");
        }
        Airport airport = new Airport(
                airportRequest.getIata(),
                airportRequest.getName(),
                airportRequest.getAirportGroupCode(),
                airportRequest.getLanguage(),
                airportRequest.getPriority()
        );
        return airportRepository.save(airport);
    }

    public void deleteAirport(String iata) {
        airportRepository.deleteById(iata);
    }

    public Airport updateAirports(String iata, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(iata).orElseThrow(() -> new RuntimeException("Airport not found!"));

        airport.setName(airportRequest.getName());
        airport.setAirportGroupCode(airportRequest.getAirportGroupCode());
        airport.setLanguage(airportRequest.getLanguage());
        airport.setPriority(airportRequest.getPriority());

        return airportRepository.save(airport);
    }

    public Airport updatePathAirports(String iata, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(iata)
                .orElseThrow(() -> new RuntimeException("Airport not found with IATA: " + iata));

        if (airportRequest.getName() != null) {
            airport.setName(airportRequest.getName());
        }
        if (airportRequest.getAirportGroupCode() != null) {
            airport.setAirportGroupCode(airportRequest.getAirportGroupCode());
        }
        if (airportRequest.getLanguage() != null) {
            airport.setLanguage(airportRequest.getLanguage());
        }
        if (airportRequest.getPriority() != null) {
            airport.setPriority(airportRequest.getPriority());
        }

        return airportRepository.save(airport);
    }
}
