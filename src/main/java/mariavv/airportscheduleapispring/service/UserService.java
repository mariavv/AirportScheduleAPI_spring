package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto addUser(String name, String password);

    void deleteUser(Integer id);

    void changePassword(String name, String oldPassword, String password);

    boolean grantRole(Integer userId, Integer roleId);
}
