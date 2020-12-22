package mariavv.airportscheduleapispring.domain.dto;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Date;

@Getter
public final class FlightDto {
    private final Integer airportFromId;
    private final Integer airportToId;
    private final Date departure;
    private final Date arrival;
    private final LocalTime delay;
    private final LocalTime delayArrival;
    private final Integer postponedOn;
    private final Boolean isCanceled;

    public FlightDto(Integer airportFromId, Integer airportToId,
                     Date departure, Date arrival, LocalTime delay, LocalTime delayArrival,
                      Integer postponedOn, Boolean isCanceled) {
        this.airportFromId = airportFromId;
        this.airportToId = airportToId;
        this.departure = departure;
        this.arrival = arrival;
        this.delay = delay;
        this.delayArrival = delayArrival;
        this.postponedOn = postponedOn;
        this.isCanceled = isCanceled;
    }
}
