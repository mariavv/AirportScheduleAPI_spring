package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndArrivalIntervalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndFactArrivalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;

import java.util.List;

public interface FlightService {
    List<FlightWithIdResponse> getFlights();

    Boolean createFlight(FlightRequest flight);

    void deleteFlight(Integer id);

    Boolean updateIsCanceled(Integer id, Boolean isCancelled);

    List<FlightWithIdResponse> findByAirportFromAndAirportToAndArrivalBetween(AirportsAndArrivalIntervalRequest target);

    List<FlightWithIdResponse> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalRequest factArrival);
}