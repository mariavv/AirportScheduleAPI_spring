package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.PermissionDto;
import mariavv.airportscheduleapispring.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('auth')")
    @GetMapping()
    public List<PermissionDto> getPermissionss() {
        return service.getPermissions();
    }

    @PreAuthorize("hasAuthority('auth')")
    @PostMapping("/{name}")
    public PermissionDto create(@PathVariable String name) {
        return service.addPermission(name);
    }

    @PreAuthorize("hasAuthority('auth')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deletePermission(id);
    }
}
