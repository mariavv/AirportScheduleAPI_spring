package mariavv.airportscheduleapispring.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AirportNameDto {
    private final String name;

    @JsonCreator
    public AirportNameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}