package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.AirportDto;

import java.util.List;

public interface AirportService {
    List<AirportDto> getAirports();

    Integer getAirportIdByName(String name);

    AirportDto addAirport(String town);

    void deleteAirport(Integer id);
}
