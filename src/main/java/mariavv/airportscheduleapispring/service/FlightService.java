package mariavv.airportscheduleapispring.service;

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

    List<FlightWithIdDto> findByAirportFromAndAirportToAndArrivalBetween(Integer airportFromId, Integer airportToId,
                                                                         Date arrivalFrom, Date arrivalTo);

    List<FlightWithIdDto> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalDto factArrival);
}