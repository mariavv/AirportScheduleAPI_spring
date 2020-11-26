package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    private final AirportService service;

    public AirportController(AirportService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('schedule:read')")
    @GetMapping()
    public List<AirportDto> getAirports() {
        return service.getAirports();
    }

    @PreAuthorize("hasAuthority('schedule:read')")
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