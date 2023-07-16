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
import tu.carshop.dtos.UserProfileDTO;
import tu.carshop.dtos.UserRegisterDTO;
import tu.carshop.enums.Role;
import tu.carshop.mapper.UserMapper;
import tu.carshop.models.AppUser;
import tu.carshop.models.User;
import tu.carshop.models.UserRole;
import tu.carshop.repositories.UserRepository;
import tu.carshop.repositories.UserRoleRepository;

import java.time.LocalDateTime;
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
        User user = findByUsername(username);

        return new AppUser(
            user.getUsername(),
            user.getPassword(),
            getAuthorities(user)
        );
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, username)));
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles()
            .stream()
            .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name()))
            .collect(Collectors.toList());
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = userMapper.toEntity(userRegisterDTO);

        user.setRoles(List.of(getUserRole()));
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setImageURL("https://www.vecteezy.com/free-vector/user-profile");

        userRepository.save(user);
    }

    private UserRole getUserRole() {
        return userRoleRepository.findAll()
            .stream()
            .filter(role -> role.getRole() == Role.USER)
            .findAny()
            .orElseThrow();
    }

    public UserProfileDTO getUserProfile(String username) {
        User user = findByUsername(username);
        return userMapper.toDTO(user);
    }

    public void update(UserProfileDTO userProfileDTO) {
        User user = findByUsername(userProfileDTO.getUsername());

        user.setUsername(userProfileDTO.getUsername());
        user.setFirstName(userProfileDTO.getFirstName());
        user.setLastName(userProfileDTO.getLastName());
        user.setEmail(userProfileDTO.getEmail());
        user.setImageURL(userProfileDTO.getImageURL());
        user.setModified(LocalDateTime.now());

        userRepository.save(user);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
