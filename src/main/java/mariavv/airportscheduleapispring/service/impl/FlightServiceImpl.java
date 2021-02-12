package mariavv.airportscheduleapispring.service.impl;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndArrivalIntervalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.AirportsAndFactArrivalRequest;
import mariavv.airportscheduleapispring.domain.dto.request.FlightRequest;
import mariavv.airportscheduleapispring.domain.dto.response.FlightWithIdResponse;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import mariavv.airportscheduleapispring.exception.NotFoundException;
import mariavv.airportscheduleapispring.mapper.AnotherFlightMapper;
import mariavv.airportscheduleapispring.mapper.FlightMapper;
import mariavv.airportscheduleapispring.repo.AirportRepository;
import mariavv.airportscheduleapispring.repo.FlightRepository;
import mariavv.airportscheduleapispring.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AnotherFlightMapper anotherFlightMapper;

    @Transactional(readOnly = true)
    @Override
    public List<FlightWithIdResponse> getFlights() {
        return flightRepository.findAll()
                .stream()
                .map(anotherFlightMapper::toFlightWithIdDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createFlight(FlightRequest flight) {
        Integer postponedOnId = flight.getPostponedOn();
        FlightEntity flightEntity = anotherFlightMapper.toFlightEntity(
                flight,
                airportRepository.findById(flight.getAirportFromId()).get(),
                airportRepository.findById(flight.getAirportToId()).get(),
                postponedOnId == null ? null : flightRepository.findById(postponedOnId).orElse(null)
        );

        flightRepository.save(flightEntity);
    }

    @Override
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }

    @Override
    public void updateIsCanceled(Integer id, Boolean isCancelled) {
        FlightEntity flight = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Flight with id = %d not found", id)));

        flight.setIsCanceled(isCancelled);
        flightRepository.save(flight);
    }

    AirportEntity getIt(Integer id) {
        return airportRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Airport with id = %d not found", id))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<FlightWithIdResponse>> findByAirportFromAndAirportToAndArrivalBetween(AirportsAndArrivalIntervalRequest params) {
        Optional<AirportEntity> airportFrom = airportRepository.findById(params.getAirportFromId());

        Optional<AirportEntity> airportTo = airportRepository.findById(params.getAirportToId());

        if (airportFrom.isEmpty() || airportTo.isEmpty()) {
            return Optional.of(null);
        }

        //Assert.notNull(params.getArrivalFrom(), "arrivalFrom date must not be null");
        //Assert.notNull(params.getArrivalTo(), "arrivalTo date must not be null");

        List<FlightEntity> flights = flightRepository.findByAirportFromAndAirportToAndArrivalBetween(
                airportFrom,
                airportTo,
                params.getArrivalFrom(),
                params.getArrivalTo()
        );

        return Optional.of(anotherFlightMapper.toDtoList(flights));
    }

    @Transactional(readOnly = true)
    @Override
    public List<FlightWithIdResponse> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalRequest params) {
        airportRepository.findById(params.getAirportFromId())
                .orElseThrow(() -> new NotFoundException(String.format("Airport with id = %d not found", params.getAirportFromId())));
        airportRepository.findById(params.getAirportToId())
                .orElseThrow(() -> new NotFoundException(String.format("Airport with id = %d not found", params.getAirportToId())));

        return anotherFlightMapper.toDtoList(flightRepository.getFlightsByAirportsAndArrivalWithDelays(
                params.getAirportFromId(), params.getAirportToId(), params.getArrivalTo()));
    }

    /*private AirportEntity getArportById(Integer id) {
        return airportRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Airport with id = %d not found", params.getAirportFromId()));
    }*/
}