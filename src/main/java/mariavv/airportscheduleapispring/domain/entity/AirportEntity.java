package mariavv.airportscheduleapispring.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "airport")
@Data
public class AirportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public AirportEntity() {
    }
}