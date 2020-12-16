package mariavv.airportscheduleapispring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AirportDto {
    private final Integer id;
    private final String name;
}
