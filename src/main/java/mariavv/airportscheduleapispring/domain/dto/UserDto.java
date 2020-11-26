package mariavv.airportscheduleapispring.domain.dto;

import java.util.Set;

public class UserDto {

    private final Integer id;
    private final String name;
    private final Set<String> roles;

    public UserDto(Integer id, String name, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
