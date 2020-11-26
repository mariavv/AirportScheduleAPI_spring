package mariavv.airportscheduleapispring.service.impl;

import mariavv.airportscheduleapispring.domain.dto.UserDto;
import mariavv.airportscheduleapispring.domain.entity.PermissionEntity;
import mariavv.airportscheduleapispring.domain.entity.UserEntity;
import mariavv.airportscheduleapispring.repo.UserRepository;
import mariavv.airportscheduleapispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
    public UserDto addUser(String name) {
        UserEntity user = new UserEntity();
        user.setName(name);
        //todo add password
        userRepository.save(user);

        return new UserDto(user.getId(), user.getName(), null);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(String name, String password) {
        //todo
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(s).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", s));
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
