package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.RoleDto;
import mariavv.airportscheduleapispring.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('auth')")
    @GetMapping()
    public List<RoleDto> getRoles() {
        return service.getRoles();
    }

    @PreAuthorize("hasAuthority('auth')")
    @PostMapping("/{name}")
    public RoleDto create(@PathVariable String name) {
        return service.addRole(name);
    }

    @PreAuthorize("hasAuthority('auth')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteRole(id);
    }

    @PreAuthorize("hasAuthority('auth')")
    @PostMapping("/grant")
    public ResponseEntity<String> grantPermission(@RequestParam Integer roleId, @RequestParam Integer permissionId) {
        if (isEmpty(roleId) || isEmpty((permissionId))) {
            return ResponseEntity.badRequest().build();
        }

        if (service.grantPermission(roleId, permissionId)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
