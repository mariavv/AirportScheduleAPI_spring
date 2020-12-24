package mariavv.airportscheduleapispring.controller;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndArrivalIntervalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndFactArrivalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService service;

    @GetMapping
    public List<FlightWithIdResponse> flights() {
        return service.getFlights();
    }

    @PreAuthorize("hasAuthority('schedule:write')")
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody FlightRequest flight) {
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
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,
                                         @RequestParam Boolean isCanceled) {
        if (isEmpty(isCanceled)) {
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
    public ResponseEntity<List<FlightWithIdResponse>> getFlightsByAirportAndArrivalInterval(@RequestBody AirportsAndArrivalIntervalRequest target) {
        /*if (isEmpty(target.getAirportFromId()) || isEmpty(target.getAirportToId()) ||
                isEmpty(target.getArrivalFrom()) || isEmpty(target.getArrivalTo())) {
            return ResponseEntity.badRequest().build();
        }*/

        return ResponseEntity.ok(service.findByAirportFromAndAirportToAndArrivalBetween(target));
    }

    @GetMapping("/by-airport-and-arrival-delay")
    public ResponseEntity<List<FlightWithIdResponse>> getFlightsByAirportsAndArrivalWithDelays(@RequestBody AirportsAndFactArrivalRequest target) {
        return ResponseEntity.ok(service.getFlightsByAirportsAndArrivalWithDelays(target));
    }
}