package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.AirportsAndArrivalInterval;
import mariavv.airportscheduleapispring.domain.dto.AirportsAndFactArrival;
import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.FlightWithIdDto;
import mariavv.airportscheduleapispring.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public List<FlightWithIdDto> flights() {
        return flightService.getFlights();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody FlightDto flight) {
        if (isEmpty(flight)) {
            return ResponseEntity.badRequest().build();
        }

        if (flightService.createFlight(flight)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestParam Integer id,
                                         @RequestParam Boolean isCanceled) {
        if (isEmpty(id) || isEmpty(isCanceled)) {
            return ResponseEntity.badRequest().build();
        }

        flightService.updateIsCanceled(id, isCanceled);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/by-airport-and-arrival")
    public ResponseEntity<List<FlightWithIdDto>> getFlightsByAirportAndArrivalInterval(@RequestBody AirportsAndArrivalInterval target) {
        if (isEmpty(target.getAirportFromId()) || isEmpty(target.getAirportToId()) ||
                isEmpty(target.getArrivalFrom()) || isEmpty(target.getArrivalTo())) {
            return ResponseEntity.badRequest().build();
        }

        List<FlightWithIdDto> result =
                flightService.findByAirportFromAndAirportToAndArrivalBetween(target.getAirportFromId(),
                        target.getAirportToId(), target.getArrivalFrom(), target.getArrivalTo());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-airport-and-arrival-delay")
    public ResponseEntity<List<FlightWithIdDto>> getFlightsByAirportsAndArrivalWithDelays(@RequestBody AirportsAndFactArrival target) {

        List<FlightWithIdDto> result = flightService.getFlightsByAirportsAndArrivalWithDelays(target);
        return ResponseEntity.ok(result);
    }
}