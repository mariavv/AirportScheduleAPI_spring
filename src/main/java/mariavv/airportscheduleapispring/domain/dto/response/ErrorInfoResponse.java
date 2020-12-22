package mariavv.airportscheduleapispring.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorInfoResponse {
    private final String message;
}
