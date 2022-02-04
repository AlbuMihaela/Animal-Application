package com.sda.project.service;

import com.sda.project.config.security.UserPrincipal;
import com.sda.project.controller.exception.ResourceAlreadyExistsException;
import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.AdoptionDto;
import com.sda.project.dto.UserDto;
import com.sda.project.mapper.UserMapper;
import com.sda.project.model.Role;
import com.sda.project.model.RoleType;
import com.sda.project.model.User;
import com.sda.project.repository.RoleRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(UserDto userDto) {
        log.info("save user {}", userDto);
        String email = userDto.getEmail();
        userRepository.findByEmail(email)
                .map(existingUser -> {
                    log.error("user with email {} already exists", email);
                    throw new ResourceAlreadyExistsException("user with email " + email + " already exists");
                })
                .orElseGet(() -> saveUser(userDto));
    }

    private User saveUser(UserDto userDto) {
        Role userRole = roleRepository.findByType(RoleType.USER)
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));
        // convert user dto to user
        User user = userMapper.mapToUser(userDto);
        // encode password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.addRole(userRole);
        return userRepository.save(user);
    }

    public List<UserDto> findAll() {
        log.info("find list of userDto");
        return (userRepository.findAll().stream()
                .map(user -> userMapper.mapToUserDto(user))
                .collect(Collectors.toList()));
    }

    public User findByEmail(String email) {
        log.info("find user by email {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }

    public User findLoggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).
                orElseThrow(()-> new ResourceNotFoundException("user not found"));
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> {
                    Set<Role> roles = roleRepository.getRoles(user.getId());
                    return new UserPrincipal(user, roles);
                })
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }

    public User findById(long id) {
        log.info("find user {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public void update(User user) {
        log.info("update user {}", user);
        userRepository.save(user);
    }

    public void updateByUserId( Long id){
        User user = findById(id);
        update(user);
    }

    public void enable(Long id) {
        log.info("enable user {}", id);
        userRepository.findById(id)
                .map(foundUser -> {
                    foundUser.setEnabled(true);
                    return userRepository.save(foundUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public void disable(Long id) {
        log.info("disable user {}", id);
        Role adminRole = roleRepository.findByType(RoleType.ADMIN)
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));
        long enabledAdminsCount = userRepository.findAll().stream()
                .filter(user -> user.isEnabled())
                .filter(user -> user.getRoles().contains(adminRole))
                .count();
        if (enabledAdminsCount > 1) {
            userRepository.findById(id)
                    .map(foundUser -> {
                        foundUser.setEnabled(false);
                        return userRepository.save(foundUser);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        } else {
            throw new RuntimeException("can't disable last admin");
        }
    }

}
