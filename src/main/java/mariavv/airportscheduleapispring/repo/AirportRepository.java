package mariavv.airportscheduleapispring.repo;

import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Integer> {
    AirportEntity findByName(String name);
}