package mariavv.airportscheduleapispring.domain.model;

public enum Permission {
    AIRPORT_READ("airport:read"),
    AIRPORT_WRITE("airport:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
