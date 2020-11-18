package mariavv.airportscheduleapispring.repo;

import mariavv.airportscheduleapispring.domain.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Integer> {
    @Query(value = "SELECT DISTINCT a.id FROM airport a WHERE a.name = :name"
            , nativeQuery = true)
    Integer findIdByName(String name);
}