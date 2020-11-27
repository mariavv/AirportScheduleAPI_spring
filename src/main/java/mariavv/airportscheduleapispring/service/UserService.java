package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.UserDto;
import mariavv.airportscheduleapispring.domain.dto.UserPassDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto addUser(UserPassDto newUser);

    void deleteUser(Integer id);

    void changePassword(String name, String password, String newPassword);

    boolean grantRole(Integer userId, Integer roleId);
}
