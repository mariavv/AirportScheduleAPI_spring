package mariavv.airportscheduleapispring.controller;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.AirportResponse;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@AllArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping()
    public List<AirportResponse> getAirports() {
        return airportService.getAirports();
    }

    @GetMapping("/{name}")
    public Integer getOne(@PathVariable String name) {
        return airportService.getAirportIdByName(name);
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @PostMapping("/{name}")
    public AirportResponse create(@PathVariable String name) {
        return airportService.addAirport(name);
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        airportService.deleteAirport(id);
    }
}