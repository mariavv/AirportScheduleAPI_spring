package mariavv.airportscheduleapispring.domain.dto;

public class AirportDto {
    private final Integer id;
    private final String name;

    public AirportDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}