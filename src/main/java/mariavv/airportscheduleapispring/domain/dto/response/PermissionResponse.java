package mariavv.airportscheduleapispring.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PermissionResponse {
    private final Integer id;
    private final String permission;
}
