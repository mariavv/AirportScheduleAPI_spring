package mariavv.airportscheduleapispring.service.impl;

import mariavv.airportscheduleapispring.domain.dto.UserDto;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.domain.entity.RoleEntity;
import mariavv.airportscheduleapispring.domain.entity.UserEntity;
import mariavv.airportscheduleapispring.exception.NotFoundException;
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
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), null/*todo*/))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(String name, String password) {
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return new UserDto(user.getId(), user.getName(), null);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(String name, String oldPassword, String password) {
        UserEntity user = userRepository.findByName(name).orElse(null);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
        } else {
            //todo PassNotMatchesEx
            throw new NotFoundException("");
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
