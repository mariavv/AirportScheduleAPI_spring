package mariavv.airportscheduleapispring.repo;

import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
}
