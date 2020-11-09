package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;
import mariavv.airportscheduleapispring.domain.dto.AirportNameDto;

import java.util.List;

public interface AirportService {
    List<AirportDto> getAirports();

    Integer getAirportByName(String name);

    AirportDto addAirport(AirportNameDto town);
}
