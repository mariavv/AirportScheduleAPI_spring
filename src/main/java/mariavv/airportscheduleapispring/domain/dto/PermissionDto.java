package mariavv.airportscheduleapispring.domain.dto;

public class PermissionDto {

    private final Integer id;
    private final String permission;

    public PermissionDto(Integer id, String permission) {
        this.id = id;
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }
}
