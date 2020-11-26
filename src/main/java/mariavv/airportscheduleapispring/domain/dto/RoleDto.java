package mariavv.airportscheduleapispring.domain.dto;

public class RoleDto {

    private final Integer id;
    private final String role;

    public RoleDto(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
