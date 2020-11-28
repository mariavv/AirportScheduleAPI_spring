package mariavv.airportscheduleapispring.service.impl;

import mariavv.airportscheduleapispring.domain.dto.PermissionDto;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.repo.PermissionRepository;
import mariavv.airportscheduleapispring.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<PermissionDto> getPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permission -> new PermissionDto(permission.getId(), permission.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDto addPermission(String name) {
        PermissionEntity permission = new PermissionEntity();
        permission.setPermission(name);
        permissionRepository.save(permission);

        return new PermissionDto(permission.getId(), permission.getPermission());
    }

    @Override
    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }
}
