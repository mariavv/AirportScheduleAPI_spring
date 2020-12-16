package mariavv.airportscheduleapispring.controller;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@AllArgsConstructor
public class AirportController {

    private final AirportService service;

    @GetMapping()
    public List<AirportDto> getAirports() {
        return service.getAirports();
    }

    @GetMapping("/{name}")
    public Integer getOne(@PathVariable String name) {
        return service.getAirportIdByName(name);
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @PostMapping("/{name}")
    public AirportDto create(@PathVariable String name) {
        return service.addAirport(name);
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteAirport(id);
    }
}