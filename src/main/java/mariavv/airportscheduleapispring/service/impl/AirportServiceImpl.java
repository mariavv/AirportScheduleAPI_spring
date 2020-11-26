package mariavv.airportscheduleapispring.service.impl;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.exception.NotFoundException;
import mariavv.airportscheduleapispring.repo.AirportRepository;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<AirportDto> getAirports() {
        return airportRepository.findAll()
                .stream()
                .map(town -> new AirportDto(town.getId(), town.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getAirportIdByName(String name) {
        AirportEntity airport = airportRepository.findByName(name);
        if (airport == null) {
            throw new NotFoundException(String.format("Airport with name '%s' not found", name));
        }
        return airport.getId();
    }

    @Override
    public AirportDto addAirport(String name) {
        AirportEntity airport = new AirportEntity();
        airport.setName(name);
        airportRepository.save(airport);

        return new AirportDto(airport.getId(), airport.getName());
    }

    @Override
    public void deleteAirport(Integer id) {
        airportRepository.deleteById(id);
    }
}