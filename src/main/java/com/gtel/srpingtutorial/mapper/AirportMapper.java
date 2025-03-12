package com.gtel.srpingtutorial.mapper;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.model.Airport;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toAirport(AirportRequest request);
    AirportResponse toAirportResponse(Airport airport);
    List<AirportResponse> toListAirportResponse(List<Airport> airports);

    @Mapping(target = "iata", ignore = true)
    void updateAirport(@MappingTarget Airport airport, AirportRequest airportRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatchAirport(@MappingTarget Airport airport, AirportRequest airportRequest);

}
