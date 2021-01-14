package mariavv.airportscheduleapispring.service.impl;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.PermissionResponse;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.repo.PermissionRepository;
import mariavv.airportscheduleapispring.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PermissionResponse> getPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permission -> new PermissionResponse(permission.getId(), permission.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponse addPermission(String name) {
        PermissionEntity permission = new PermissionEntity();
        permission.setPermission(name);
        permissionRepository.save(permission);

        return new PermissionResponse(permission.getId(), permission.getPermission());
    }

    @Override
    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }
}
