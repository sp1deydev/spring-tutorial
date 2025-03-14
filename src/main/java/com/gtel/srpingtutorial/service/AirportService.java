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
        return airportMapper.toListAirportResponse(airportRepository.getAirports(page, size));
    }

    public int countAirports() {
        return airportRepository.countAirports();
    }

    public AirportResponse getAirport(String iata) {
        return airportMapper.toAirportResponse(getAirportInfo(iata));
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        if(airportRepository.isExisted(airportRequest.getIata())) {
            throw new RuntimeException("Airport is existed!");
        }
        Airport airport = airportMapper.toAirport(airportRequest);
        return airportMapper.toAirportResponse(airportRepository.createAirport(airport));
    }

    public void deleteAirport(String iata) {
        airportRepository.deleteAirport(iata);
    }

    public AirportResponse updateAirports(String iata, AirportRequest airportRequest) {
        Airport airport = getAirportInfo(iata);
        airportMapper.updateAirport(airport, airportRequest);

        return airportMapper.toAirportResponse(airportRepository.updateAirport(airport));
    }

    public AirportResponse updatePatchAirports(String iata, AirportRequest airportRequest) {
        Airport airport = getAirportInfo(iata);
        airportMapper.updatePatchAirport(airport, airportRequest);

        return airportMapper.toAirportResponse(airportRepository.updatePatchAirport(airport));
    }

    private Airport getAirportInfo(String iata) {
        Airport airport = airportRepository.getAirport(iata);
        if(airport == null) {
            throw new RuntimeException("Airport not found!");
        }
        return airport;
    }

}
