package mariavv.airportscheduleapispring.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalTime;
import java.util.Date;

@Getter
public final class FlightDto {
    @Min(0)
    private final Integer airportFromId;
    @Min(0)
    private final Integer airportToId;
    @Future
    private final Date departure;
    @Future
    private final Date arrival;
    private final LocalTime delay;
    private final LocalTime delayArrival;
    private final Integer postponedOn;
    private final Boolean isCanceled;

    @JsonCreator
    public FlightDto(@JsonProperty(value = "airportFromId") Integer airportFromId,
                     @JsonProperty(value = "airportToId") Integer airportToId,
                     @JsonProperty(value = "departure") Date departure,
                     @JsonProperty(value = "arrival") Date arrival,
                     @JsonProperty(value = "delay") LocalTime delay,
                     @JsonProperty(value = "delayArrival") LocalTime delayArrival,
                     @JsonProperty(value = "postponedOn") Integer postponedOn,
                     @JsonProperty(value = "isCanceled") Boolean isCanceled) {
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
