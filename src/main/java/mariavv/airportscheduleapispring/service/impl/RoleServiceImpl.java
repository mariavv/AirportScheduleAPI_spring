package mariavv.airportscheduleapispring.service.impl;

import mariavv.airportscheduleapispring.domain.dto.RoleDto;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.domain.entity.RoleEntity;
import mariavv.airportscheduleapispring.repo.PermissionRepository;
import mariavv.airportscheduleapispring.repo.RoleRepository;
import mariavv.airportscheduleapispring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<RoleDto> getRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> new RoleDto(role.getId(), role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto addRole(String name) {
        RoleEntity role = new RoleEntity();
        role.setRole(name);
        roleRepository.save(role);

        return new RoleDto(role.getId(), role.getRole());
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
