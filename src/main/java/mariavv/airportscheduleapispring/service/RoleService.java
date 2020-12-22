package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getRoles();

    RoleResponse addRole(String name);

    void deleteRole(Integer id);

    boolean grantPermission(Integer roleId, Integer permissionId);
}
