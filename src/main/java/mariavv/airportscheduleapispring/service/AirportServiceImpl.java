package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.domain.dto.AirportNameDto;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.exception.NotFoundException;
import mariavv.airportscheduleapispring.repo.AirportRepository;
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

    public Integer getAirportByName(String name) {
        AirportEntity airport = airportRepository.findByName(name);
        if (airport == null) {
            throw new NotFoundException();
        }
        return airport.getId();
    }

    public AirportDto addAirport(AirportNameDto town) {
        AirportEntity airport = new AirportEntity();
        airport.setName(town.getName());
        airportRepository.save(airport);

        return new AirportDto(airport.getId(), airport.getName());
    }
}