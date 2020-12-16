package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.AirportsAndArrivalIntervalDto;
import mariavv.airportscheduleapispring.domain.dto.AirportsAndFactArrivalDto;
import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.FlightWithIdDto;

import java.util.Date;
import java.util.List;

public interface FlightService {
    List<FlightWithIdDto> getFlights();

    Boolean createFlight(FlightDto flight);

    void deleteFlight(Integer id);

    Boolean updateIsCanceled(Integer id, Boolean isCancelled);

    List<FlightWithIdDto> findByAirportFromAndAirportToAndArrivalBetween(AirportsAndArrivalIntervalDto target);

    List<FlightWithIdDto> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalDto factArrival);
}