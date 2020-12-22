package mariavv.airportscheduleapispring.controller;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.request.ChangePasswordRequest;
import mariavv.airportscheduleapispring.domain.dto.request.UserPassRequest;
import mariavv.airportscheduleapispring.domain.dto.response.UserResponse;
import mariavv.airportscheduleapispring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PreAuthorize("hasAuthority('auth')")
    @GetMapping()
    public List<UserResponse> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserPassRequest user) {
        if (isEmpty(user.getName()) || isEmpty(user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(service.addUser(user));
    }

    @PreAuthorize("hasAuthority('auth')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('schedule:read')")
    @PostMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest pass) {
        service.changePassword(pass.getName(), pass.getPassword(), pass.getNewPassword());
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
