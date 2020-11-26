package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto addUser(String name);

    void deleteUser(Integer id);

    void changePassword(String name, String password);
}
