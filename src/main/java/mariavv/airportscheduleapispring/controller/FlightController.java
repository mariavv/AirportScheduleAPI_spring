package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.AirportsAndArrivalInterval;
import mariavv.airportscheduleapispring.domain.dto.AirportsAndFactArrival;
import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.FlightWithIdDto;
import mariavv.airportscheduleapispring.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('schedule:read')")
    @GetMapping()
    public List<FlightWithIdDto> flights() {
        return service.getFlights();
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody FlightDto flight) {
        if (isEmpty(flight)) {
            return ResponseEntity.badRequest().build();
        }

        if (service.createFlight(flight)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @PutMapping()
    public ResponseEntity<String> update(@RequestParam Integer id,
                                         @RequestParam Boolean isCanceled) {
        if (isEmpty(id) || isEmpty(isCanceled)) {
            return ResponseEntity.badRequest().build();
        }

        service.updateIsCanceled(id, isCanceled);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteFlight(id);
    }

    @PreAuthorize("hasAuthority('schedule:read')")
    @GetMapping("/by-airport-and-arrival")
    public ResponseEntity<List<FlightWithIdDto>> getFlightsByAirportAndArrivalInterval(@RequestBody AirportsAndArrivalInterval target) {
        if (isEmpty(target.getAirportFromId()) || isEmpty(target.getAirportToId()) ||
                isEmpty(target.getArrivalFrom()) || isEmpty(target.getArrivalTo())) {
            return ResponseEntity.badRequest().build();
        }

        List<FlightWithIdDto> result =
                service.findByAirportFromAndAirportToAndArrivalBetween(target.getAirportFromId(),
                        target.getAirportToId(), target.getArrivalFrom(), target.getArrivalTo());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('schedule:read')")
    @GetMapping("/by-airport-and-arrival-delay")
    public ResponseEntity<List<FlightWithIdDto>> getFlightsByAirportsAndArrivalWithDelays(@RequestBody AirportsAndFactArrival target) {

        List<FlightWithIdDto> result = service.getFlightsByAirportsAndArrivalWithDelays(target);
        return ResponseEntity.ok(result);
    }
}