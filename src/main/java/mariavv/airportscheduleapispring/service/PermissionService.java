package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> getPermissions();

    PermissionDto addPermission(String name);

    void deletePermission(Integer id);
}
