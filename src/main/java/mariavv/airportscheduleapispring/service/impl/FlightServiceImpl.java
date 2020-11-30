package mariavv.airportscheduleapispring.service.impl;

import mariavv.airportscheduleapispring.domain.dto.AirportsAndFactArrivalDto;
import mariavv.airportscheduleapispring.domain.dto.FlightDto;
import mariavv.airportscheduleapispring.domain.dto.FlightWithIdDto;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import mariavv.airportscheduleapispring.mapper.FlightMapper;
import mariavv.airportscheduleapispring.repo.AirportRepository;
import mariavv.airportscheduleapispring.repo.FlightRepository;
import mariavv.airportscheduleapispring.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class FlightServiceImpl implements FlightService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(AirportRepository airportRepository, FlightRepository flightRepository, FlightMapper flightMapper) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<FlightWithIdDto> getFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean createFlight(FlightDto flight) {
        FlightEntity flightEntity = new FlightEntity();

        flightEntity.setAirportFrom(airportRepository.findById(flight.getAirportFromId()).get());
        flightEntity.setAirportTo(airportRepository.findById(flight.getAirportToId()).get());

        flightEntity.setDeparture(flight.getDeparture());
        flightEntity.setArrival(flight.getArrival());

        flightEntity.setDelay(flight.getDelay());
        flightEntity.setDelayArrival(flight.getDelayArrival());

        Integer postponedOnId = flight.getPostponedOn();
        FlightEntity postponedOn = postponedOnId == null ? null : flightRepository.findById(postponedOnId).orElse(null);
        flightEntity.setPostponedOn(postponedOn);

        flightEntity.setIsCanceled(flight.getIsCanceled());

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
    public List<FlightWithIdDto> findByAirportFromAndAirportToAndArrivalBetween(Integer airportFromId,
                                                                                Integer airportToId,
                                                                                Date arrivalFrom,
                                                                                Date arrivalTo) {
        Optional<AirportEntity> airportFrom = airportRepository.findById(airportFromId);
        Optional<AirportEntity> airportTo = airportRepository.findById(airportToId);
        if (airportFrom.isEmpty() || airportTo.isEmpty()) {
            return new ArrayList<>();
        }

        List<FlightEntity> flights = flightRepository
                .findByAirportFromAndAirportToAndArrivalBetween(airportFrom.get(), airportTo.get(), arrivalFrom, arrivalTo);

        return flightMapper.toDtoList(flights);
    }

    @Override
    public List<FlightWithIdDto> getFlightsByAirportsAndArrivalWithDelays(AirportsAndFactArrivalDto params) {
        return flightMapper.toDtoList(flightRepository.getFlightsByAirportsAndArrivalWithDelays(
                params.getAirportFromId(), params.getAirportToId(), params.getArrivalTo()));
    }
}