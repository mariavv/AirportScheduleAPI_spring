package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PreAuthorize("hasAuthority('airport:read')")
    @GetMapping()
    public List<AirportDto> getAirports() {
        return airportService.getAirports();
    }

    @PreAuthorize("hasAuthority('airport:read')")
    @GetMapping("/{name}")
    public Integer getOne(@PathVariable String name) {
        return airportService.getAirportIdByName(name);
    }

    @PreAuthorize("hasAuthority('airport:write')")
    @PostMapping("/{name}")
    public AirportDto create(@PathVariable String name) {
        return airportService.addAirport(name);
    }

    @PreAuthorize("hasAuthority('airport:write')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        airportService.deleteAirport(id);
    }
}