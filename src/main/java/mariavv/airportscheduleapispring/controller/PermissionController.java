package mariavv.airportscheduleapispring.controller;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.response.PermissionResponse;
import mariavv.airportscheduleapispring.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@AllArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PreAuthorize("hasAuthority('auth')")
    @GetMapping()
    public List<PermissionResponse> getPermissionss() {
        return permissionService.getPermissions();
    }

    @PreAuthorize("hasAuthority('auth')")
    @PostMapping("/{name}")
    public PermissionResponse create(@PathVariable String name) {
        return permissionService.addPermission(name);
    }

    @PreAuthorize("hasAuthority('auth')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        permissionService.deletePermission(id);
    }
}
