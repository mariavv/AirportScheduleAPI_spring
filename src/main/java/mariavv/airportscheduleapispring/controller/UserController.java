package mariavv.airportscheduleapispring.controller;

import mariavv.airportscheduleapispring.domain.dto.UserDto;
import mariavv.airportscheduleapispring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

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
    @PostMapping("")
    public ResponseEntity<UserDto> create(@RequestParam String name, @RequestParam String password) {
        if (isEmpty(name) || isEmpty(password)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(service.addUser(name, password));
    }

    @PreAuthorize("hasAuthority('auth:write')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    @PostMapping()
    public ResponseEntity<String> password(@RequestParam String name,
                                           @RequestParam String oldPassword,
                                           @RequestParam String password) {
        service.changePassword(name, oldPassword, password);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasAuthority('auth')")
    @PostMapping("/grant")
    public ResponseEntity<String> grantRole(@RequestParam Integer userId, @RequestParam Integer roleId) {
        if (isEmpty(userId) || isEmpty((roleId))) {
            return ResponseEntity.badRequest().build();
        }

        if (service.grantRole(userId, roleId)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
