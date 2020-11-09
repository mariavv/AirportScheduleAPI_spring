package mariavv.airportscheduleapispring.entity;

public class AirportEntity {
    private Long id;
    private String name;

    public AirportEntity() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
