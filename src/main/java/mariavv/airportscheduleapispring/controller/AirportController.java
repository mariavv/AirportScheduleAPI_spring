package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.domain.dto.AirportNameDto;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public List<AirportDto> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping("/airport/{name}")
    public Integer getOne(@PathVariable String name) {
        return airportService.getAirportByName(name);
    }

    @PostMapping
    public AirportDto create(@RequestBody AirportNameDto town) {
        return airportService.addAirport(town);
    }
}