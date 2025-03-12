package com.gtel.srpingtutorial.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AirportResponse {
    private String iata;
    private String name;
    private String airportGroupCode;
    private String language;
    private Integer priority;
}
