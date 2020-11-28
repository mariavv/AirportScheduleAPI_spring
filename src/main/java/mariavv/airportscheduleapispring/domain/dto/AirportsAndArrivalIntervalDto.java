package mariavv.airportscheduleapispring.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.util.Date;

public final class AirportsAndArrivalIntervalDto {
    @Min(0)
    private final Integer airportFromId;
    @Min(0)
    private final Integer airportToId;
    @Future
    private final Date ArrivalFrom;
    @Future
    private final Date arrivalTo;

    @JsonCreator
    public AirportsAndArrivalIntervalDto(@JsonProperty(value = "airportFromId") Integer airportFromId,
                                         @JsonProperty(value = "airportToId") Integer airportToId,
                                         @JsonProperty(value = "arrivalFrom") Date arrivalFrom,
                                         @JsonProperty(value = "arrivalTo") Date arrivalTo) {
        this.airportFromId = airportFromId;
        this.airportToId = airportToId;
        this.ArrivalFrom = arrivalFrom;
        this.arrivalTo = arrivalTo;
    }

    public Integer getAirportFromId() {
        return airportFromId;
    }

    public Integer getAirportToId() {
        return airportToId;
    }

    public Date getArrivalFrom() {
        return ArrivalFrom;
    }

    public Date getArrivalTo() {
        return arrivalTo;
    }
}