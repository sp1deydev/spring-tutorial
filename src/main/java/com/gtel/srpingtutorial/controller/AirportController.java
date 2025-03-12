package com.gtel.srpingtutorial.controller;

import com.gtel.srpingtutorial.dto.request.AirportRequest;
import com.gtel.srpingtutorial.dto.response.AirportResponse;
import com.gtel.srpingtutorial.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<AirportResponse> getAirports(@RequestParam int page, @RequestParam int size) {
        return airportService.getAirports(page, size);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity countAirports() {
        int count =  airportService.countAirports();

        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(count)).build();
    }

    @GetMapping("/{iata}")
    public AirportResponse getAirport(@PathVariable String iata) {
        return airportService.getAirport(iata);
    }

    @PostMapping
    public void createAirport(@RequestBody AirportRequest airportRequest) {
        airportService.createAirport(airportRequest);
    }

    @PutMapping("/{iata}")
    public void updateAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        airportService.updateAirports(iata, airportRequest);
    }

    @PatchMapping("/{iata}")
    public void updatePatchAirport(@PathVariable String iata, @RequestBody AirportRequest airportRequest) {
        airportService.updatePathAirports(iata, airportRequest);
    }


    @DeleteMapping("/{iata}")
    public void deleteAirport(@PathVariable String iata) {
        airportService.deleteAirport(iata);
    }
}
