package com.gtel.srpingtutorial.dto.request;

import lombok.Data;

@Data
public class AirportRequest {
    private String iata;
    private String name;
    private String airportGroupCode;
    private String language;
    private Integer priority;
}
