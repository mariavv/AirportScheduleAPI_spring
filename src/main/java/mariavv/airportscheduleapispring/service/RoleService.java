package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getRoles();

    RoleDto addRole(String name);

    void deleteRole(Integer id);

    boolean grantPermission(Integer roleId, Integer permissionId);
}
