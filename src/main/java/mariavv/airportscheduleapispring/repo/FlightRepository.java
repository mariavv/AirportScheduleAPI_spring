package mariavv.airportscheduleapispring.repo;

import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import mariavv.airportscheduleapispring.domain.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {
    List<FlightEntity> findByAirportFromAndAirportToAndArrivalBetween(AirportEntity airportFromId, AirportEntity airportToId,
                                                                      Date ArrivalFrom, Date arrivalTo);

    @Query(value = "SELECT * FROM FLIGHT f " +
            " WHERE f.airport_from_id = :airportFromId and f.airport_to_id = :airportToId " +
            " and f.arrival " +
            " + (CASE WHEN f.delay IS NULL THEN '00:00' ELSE f.delay END) " +
            " + (CASE WHEN f.delay_arrival IS NULL THEN '00:00' ELSE f.delay_arrival END) " +
            " < :arrivalTo"
            , nativeQuery = true)
    List<FlightEntity> getFlightsByAirportsAndArrivalWithDelays(@Param("airportFromId") Integer airportFromId,
                                                                @Param("airportToId") Integer airportToId,
                                                                @Param("arrivalTo") Date realArrivalTo);
}