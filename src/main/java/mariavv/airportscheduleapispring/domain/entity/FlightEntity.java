package mariavv.airportscheduleapispring.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "airport_from_id", referencedColumnName = "id")
    private AirportEntity airportFrom;

    @ManyToOne
    @JoinColumn(name = "airport_to_id", referencedColumnName = "id")
    private AirportEntity airportTo;

    @Future
    @Column(name = "departure")
    private Date departure;

    @Future
    @Column(name = "arrival")
    private Date arrival;

    @Column(name = "delay")
    private LocalTime delay;

    @Column(name = "delay_arrival")
    private LocalTime delayArrival;

    @OneToOne
    @JoinColumn(name = "postponed_on_flight_id", referencedColumnName = "id")
    private FlightEntity postponedOn;

    @Column(name = "is_canceled")
    private Boolean isCanceled;

    public FlightEntity() {
    }

    public Integer getId() {
        return id;
    }

    public AirportEntity getAirportFrom() {
        return airportFrom;
    }

    public AirportEntity getAirportTo() {
        return airportTo;
    }

    public Date getDeparture() {
        return departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public LocalTime getDelay() {
        return delay;
    }

    public LocalTime getDelayArrival() {
        return delayArrival;
    }

    public FlightEntity getPostponedOn() {
        return postponedOn;
    }

    public Boolean getIsCanceled() {
        return isCanceled;
    }

    public void setAirportFrom(AirportEntity airportFrom) {
        this.airportFrom = airportFrom;
    }

    public void setAirportTo(AirportEntity airportTo) {
        this.airportTo = airportTo;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public void setDelay(LocalTime delay) {
        this.delay = delay;
    }

    public void setDelayArrival(LocalTime delayArrival) {
        this.delayArrival = delayArrival;
    }

    public void setPostponedOn(FlightEntity postponedOn) {
        this.postponedOn = postponedOn;
    }

    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((FlightEntity) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airportFrom, airportTo, departure, arrival, delay, delayArrival, postponedOn, isCanceled);
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "id=" + id +
                ", airportFrom=" + airportFrom +
                ", airportTo=" + airportTo +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", delay=" + delay +
                ", delayArrival=" + delayArrival +
                ", postponedOn=" + postponedOn +
                ", isCanceled=" + isCanceled +
                '}';
    }
}