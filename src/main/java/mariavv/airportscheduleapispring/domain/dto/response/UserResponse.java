package mariavv.airportscheduleapispring.domain.dto.response;

import lombok.Getter;

import java.util.Set;

@Getter
public class UserResponse {
    private final Integer id;
    private final String name;
    private final Set<String> roles;

    public UserResponse(Integer id, String name, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }
}
