package mariavv.airportscheduleapispring.service.impl;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.AirportResponse;
import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.exception.NotFoundException;
import mariavv.airportscheduleapispring.repo.AirportRepository;
import mariavv.airportscheduleapispring.service.AirportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AirportResponse> getAirports() {
        return airportRepository.findAll()
                .stream()
                .map(town -> new AirportResponse(town.getId(), town.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getAirportIdByName(String name) {
        AirportEntity airport = airportRepository.findByName(name);
        if (airport == null) {
            throw new NotFoundException(String.format("Airport with name '%s' not found", name));
        }
        return airport.getId();
    }

    @Override
    public AirportResponse addAirport(String name) {
        AirportEntity airport = new AirportEntity();
        airport.setName(name);
        airportRepository.save(airport);

        return new AirportResponse(airport.getId(), airport.getName());
    }

    @Override
    public void deleteAirport(Integer id) {
        airportRepository.deleteById(id);
    }
}