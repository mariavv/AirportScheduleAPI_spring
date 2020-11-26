package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.UserDto;
import mariavv.airportscheduleapispring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('auth:write')")
    @GetMapping()
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @PreAuthorize("hasAuthority('auth:write')")
    @PostMapping("/{name}")
    public UserDto create(@PathVariable String name) {
        return service.addUser(name);
    }

    @PreAuthorize("hasAuthority('auth:write')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping()
    public ResponseEntity<String> password(@RequestParam String name, @RequestParam String password) {
        service.changePassword(name, password);
        return ResponseEntity.ok().build();
    }
}
