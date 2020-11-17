package mariavv.airportscheduleapispring.domain.dto;

public class FlightWithIdDto {

    private final Integer id;
    private final FlightDto flight;

    public FlightWithIdDto(Integer id, FlightDto flight) {
        this.id = id;
        this.flight = flight;
    }

    public Integer getId() {
        return id;
    }

    public FlightDto getFlight() {
        return flight;
    }
}