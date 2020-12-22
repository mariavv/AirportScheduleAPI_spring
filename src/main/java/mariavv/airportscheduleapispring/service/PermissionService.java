package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getPermissions();

    PermissionResponse addPermission(String name);

    void deletePermission(Integer id);
}
