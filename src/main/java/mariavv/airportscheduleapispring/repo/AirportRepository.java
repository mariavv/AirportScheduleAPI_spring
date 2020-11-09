package mariavv.airportscheduleapispring.repo;

import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<AirportEntity, Integer> {
    AirportEntity findByName(String name);
}