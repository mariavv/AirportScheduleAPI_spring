package mariavv.airportscheduleapispring.service.impl;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.RoleResponse;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.domain.entity.RoleEntity;
import mariavv.airportscheduleapispring.repo.PermissionRepository;
import mariavv.airportscheduleapispring.repo.RoleRepository;
import mariavv.airportscheduleapispring.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RoleResponse> getRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> new RoleResponse(role.getId(), role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse addRole(String name) {
        RoleEntity role = new RoleEntity();
        role.setRole(name);
        roleRepository.save(role);

        return new RoleResponse(role.getId(), role.getRole());
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public boolean grantPermission(Integer roleId, Integer permissionId) {
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            return false;
        }
        PermissionEntity permission = permissionRepository.findById(permissionId).orElse(null);
        if (permission == null) {
            return false;
        }
        role.getPermissions().add(permission);
        roleRepository.save(role);
        return true;
    }
}
