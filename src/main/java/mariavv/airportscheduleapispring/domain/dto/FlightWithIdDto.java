package mariavv.airportscheduleapispring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FlightWithIdDto {
    private final Integer id;
    private final FlightDto flight;
}
