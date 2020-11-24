package mariavv.airportscheduleapispring.domain.dto;

public final class ErrorInfoDto {
    private final String message;

    public ErrorInfoDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}