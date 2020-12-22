package mariavv.airportscheduleapispring.service;

import mariavv.airportscheduleapispring.domain.dto.request.UserPassRequest;
import mariavv.airportscheduleapispring.domain.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();

    UserResponse addUser(UserPassRequest newUser);

    void deleteUser(Integer id);

    void changePassword(String name, String password, String newPassword);

    boolean grantRole(Integer userId, Integer roleId);
}
