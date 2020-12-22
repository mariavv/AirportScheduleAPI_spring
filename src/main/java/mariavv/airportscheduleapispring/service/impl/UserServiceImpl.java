package mariavv.airportscheduleapispring.service.impl;

import lombok.AllArgsConstructor;
import mariavv.airportscheduleapispring.domain.dto.request.UserPassRequest;
import mariavv.airportscheduleapispring.domain.dto.response.UserResponse;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.domain.entity.RoleEntity;
import mariavv.airportscheduleapispring.domain.entity.UserEntity;
import mariavv.airportscheduleapispring.exception.PasswordNotMatchesException;
import mariavv.airportscheduleapispring.exception.UserExistAlready;
import mariavv.airportscheduleapispring.repo.RoleRepository;
import mariavv.airportscheduleapispring.repo.UserRepository;
import mariavv.airportscheduleapispring.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getRoles()
                                .stream()
                                .map(RoleEntity::getRole)
                                .collect(Collectors.toUnmodifiableSet())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse addUser(UserPassRequest newUser) {
        if (userRepository.findByName(newUser.getName()).orElse(null) != null) {
            throw new UserExistAlready();
        }
        UserEntity user = new UserEntity();
        user.setName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getRoles()
                        .stream()
                        .map(RoleEntity::getRole)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(String name, String password, String newPassword) {
        UserEntity user = userRepository.findByName(name).orElse(null);
        assert user != null;
        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new PasswordNotMatchesException();
        }
    }

    @Override
    public boolean grantRole(Integer userId, Integer roleId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            return false;
        }
        user.getRoles().add(role);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(s).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", s));
        }

        Set<PermissionEntity> combined = user.getRoles()
                .stream()
                .flatMap(set -> set.getPermissions().stream())
                .collect(toSet());

        Set<SimpleGrantedAuthority> authorities3 = combined.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(toSet());
        return new User(user.getName(), user.getPassword(), authorities3);
    }
}
