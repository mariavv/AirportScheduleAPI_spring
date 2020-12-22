package mariavv.airportscheduleapispring.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final Integer id;
    private final String name;
    private final Set<String> roles;
}
