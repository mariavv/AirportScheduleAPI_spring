package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.response.AirportResponse;

import java.util.List;

public interface AirportService {
    List<AirportResponse> getAirports();

    Integer getAirportIdByName(String name);

    AirportResponse addAirport(String town);

    void deleteAirport(Integer id);
}
