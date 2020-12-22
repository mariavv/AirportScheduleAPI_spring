package mariavv.airportscheduleapispring.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mariavv.airportscheduleapispring.domain.dto.FlightDto;

@Getter
@AllArgsConstructor
public class FlightWithIdResponse {
    private final Integer id;
    private final FlightDto flight;
}
