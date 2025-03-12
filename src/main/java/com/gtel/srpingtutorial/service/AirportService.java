package com.gtel.srpingtutorial.service;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.mapper.AirportMapper;
import com.gtel.srpingtutorial.model.Airport;
import com.gtel.srpingtutorial.repository.AirportRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AirportService {
    AirportRepository airportRepository;
    AirportMapper airportMapper;

    public List<AirportResponse> getAirports(int page, int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return airportMapper.toListAirportResponse(airportRepository.findAll(pageable).getContent());
    }

    public int countAirports() {
        return (int) airportRepository.count();
    }

    public AirportResponse getAirport(String iata) {
        Airport airport = airportRepository.findById(iata).orElseThrow(() -> new RuntimeException("User Not Found!"));
        return airportMapper.toAirportResponse(airport);
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        if(airportRepository.existsByIata(airportRequest.getIata())) {
            throw new RuntimeException("Airport is existed!");
        }
        Airport airport = airportMapper.toAirport(airportRequest);
        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }

    public void deleteAirport(String iata) {
        airportRepository.deleteById(iata);
    }

    public AirportResponse updateAirports(String iata, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(iata).orElseThrow(() -> new RuntimeException("Airport not found!"));
        airportMapper.updateAirport(airport, airportRequest);

        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }

    public AirportResponse updatePatchAirports(String iata, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(iata)
                .orElseThrow(() -> new RuntimeException("Airport not found with IATA: " + iata));

        airportMapper.updatePatchAirport(airport, airportRequest);

        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }
}
