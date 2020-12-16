package mariavv.airportscheduleapispring.controller;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.AirportsAndArrivalIntervalDto;
import mariavv.airportscheduleapispring.domain.dto.AirportsAndFactArrivalDto;
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
@AllArgsConstructor
public class FlightController {

    private final FlightService service;

    @GetMapping
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
    @PutMapping
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

    @GetMapping("/by-airport-and-arrival")
    public ResponseEntity<List<FlightWithIdDto>> getFlightsByAirportAndArrivalInterval(@RequestBody AirportsAndArrivalIntervalDto target) {
        if (isEmpty(target.getAirportFromId()) || isEmpty(target.getAirportToId()) ||
                isEmpty(target.getArrivalFrom()) || isEmpty(target.getArrivalTo())) {
            return ResponseEntity.badRequest().build();
        }

        List<FlightWithIdDto> result =
                service.findByAirportFromAndAirportToAndArrivalBetween(target);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-airport-and-arrival-delay")
    public ResponseEntity<List<FlightWithIdDto>> getFlightsByAirportsAndArrivalWithDelays(@RequestBody AirportsAndFactArrivalDto target) {

        List<FlightWithIdDto> result = service.getFlightsByAirportsAndArrivalWithDelays(target);
        return ResponseEntity.ok(result);
    }
}