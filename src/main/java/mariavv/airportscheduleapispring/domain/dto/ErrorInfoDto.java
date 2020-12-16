package mariavv.airportscheduleapispring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorInfoDto {
    private final String message;
}
