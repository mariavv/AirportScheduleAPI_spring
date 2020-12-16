package mariavv.airportscheduleapispring.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
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
}