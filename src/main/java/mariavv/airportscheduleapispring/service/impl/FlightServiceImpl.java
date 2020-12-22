package mariavv.airportscheduleapispring.service.impl;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndArrivalIntervalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndFactArrivalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import mariavv.airportscheduleapispring.mapper.AnotherFlightMapper;
import mariavv.airportscheduleapispring.mapper.FlightMapper;
import mariavv.airportscheduleapispring.repo.AirportRepository;
import mariavv.airportscheduleapispring.repo.FlightRepository;
import mariavv.airportscheduleapispring.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AnotherFlightMapper anotherFlightMapper;

    @Override
    public List<FlightWithIdResponse> getFlights() {
        return flightRepository.findAll()
                .stream()
                .map(anotherFlightMapper::toFlightWithIdDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean createFlight(FlightRequest flight) {
        Integer postponedOnId = flight.getPostponedOn();
        FlightEntity flightEntity = anotherFlightMapper.toFlightEntity(
                flight,
                airportRepository.findById(flight.getAirportFromId()).get(),
                airportRepository.findById(flight.getAirportToId()).get(),
                postponedOnId == null ? null : flightRepository.findById(postponedOnId).orElse(null)
        );

        try {
            flightRepository.save(flightEntity);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    @Override
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Boolean updateIsCanceled(Integer id, Boolean isCancelled) {
        FlightEntity flight = flightRepository.findById(id).orElse(null);

        if (!isEmpty(flight)) {
            flight.setIsCanceled(isCancelled);
            try {
                flightRepository.save(flight);
                return true;
            } catch (IllegalArgumentException ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<FlightWithIdResponse> findByAirportFromAndAirportToAndArrivalBetween(AirportsAndArrivalIntervalRequest target) {
        Optional<AirportEntity> airportFrom = airportRepository.findById(target.getAirportFromId());
        Optional<AirportEntity> airportTo = airportRepository.findById(target.getAirportToId());
        if (airportFrom.isEmpty() || airportTo.isEmpty()) {
            return new ArrayList<>();
        }

        List<FlightEntity> flights = flightRepository
                .findByAirportFromAndAirportToAndArrivalBetween(airportFrom.get(), airportTo.get(), target.getArrivalFrom(), target.getArrivalTo());

        return anotherFlightMapper.toDtoList(flights);
    }

    @Override
    public List<FlightWithIdResponse> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalRequest params) {
        return anotherFlightMapper.toDtoList(flightRepository.getFlightsByAirportsAndArrivalWithDelays(
                params.getAirportFromId(), params.getAirportToId(), params.getArrivalTo()));
    }
}