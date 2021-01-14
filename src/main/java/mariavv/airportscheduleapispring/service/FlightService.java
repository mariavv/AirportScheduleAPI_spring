package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndArrivalIntervalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndFactArrivalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<FlightWithIdResponse> getFlights();

    void createFlight(FlightRequest flight);

    void deleteFlight(Integer id);

    void updateIsCanceled(Integer id, Boolean isCancelled);

    Optional<List<FlightWithIdResponse>> findByAirportFromAndAirportToAndArrivalBetween(AirportsAndArrivalIntervalRequest target);

    List<FlightWithIdResponse> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalRequest factArrival);
}