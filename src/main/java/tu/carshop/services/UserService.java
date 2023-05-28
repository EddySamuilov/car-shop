package tu.carshop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tu.carshop.dtos.UserRegisterDTO;
import tu.carshop.enums.Role;
import tu.carshop.mapper.UserMapper;
import tu.carshop.models.AppUser;
import tu.carshop.models.User;
import tu.carshop.models.UserRole;
import tu.carshop.repositories.UserRepository;
import tu.carshop.repositories.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found!";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, username)));

        return mapToSpringUser(user);
    }

    private static UserDetails mapToSpringUser(User user) {
        List<GrantedAuthority> authorities = user
            .getRoles()
            .stream()
            .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name()))
            .collect(Collectors.toList());

        return new AppUser(
            user.getUsername(),
            user.getPassword(),
            authorities
        );
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User entity = userMapper.toEntity(userRegisterDTO);

        UserRole userRole = getUserRole();
        entity.setRoles(List.of(userRole));

        String encodedPassword = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);

        userRepository.save(entity);
    }

    private UserRole getUserRole() {
        return userRoleRepository.findAll()
            .stream()
            .filter(role -> role.getRole() == Role.USER)
            .findAny()
            .orElseThrow();
    }
}
