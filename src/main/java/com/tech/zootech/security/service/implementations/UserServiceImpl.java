package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.RoleDto;
import com.tech.zootech.security.DTO.UserDto;
import com.tech.zootech.security.domain.User;
import com.tech.zootech.security.exceptions.UserNotFound;
import com.tech.zootech.security.repo.RoleRepo;
import com.tech.zootech.security.repo.UserRepo;
import com.tech.zootech.security.service.UserService;
import com.tech.zootech.security.utils.mapper.RoleMapper;
import com.tech.zootech.security.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUsername(username);
        checkUserForNull(username, user);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    private void checkUserForNull(String username, User user) {
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        log.info("Saving new user {} to db", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public RoleDto saveRole(RoleDto roleDto) {
        var role = roleMapper.toEntity(roleDto);
        log.info("Saving new {} to db", role.getName());
        var savedRole = roleRepo.save(role);
        return roleMapper.toDto(savedRole);
    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        var user = userRepo.findByUsername(username);
        var role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        log.info("Getting user by username: {}", username);
        var user = userRepo.findByUsername(username);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("Getting all users from db");
        return userRepo.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<UserDto> findByName(String name) {
        log.info("Getting user(s) by name: " + name);
        var users = userRepo.findByName(name)
                .orElseThrow(() -> new UserNotFound("User not found !!!"));
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<UserDto> findUserByPassword(String password) {
        log.info("Getting user(s) by password: " + password);
        var users = userRepo.findUserByPassword(password)
                .orElseThrow(() -> new UserNotFound("User not found !!!"));
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto findByCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return userMapper.toDto(userRepo.findByCreatedBetween(start, end)
                .orElseThrow(() -> new UserNotFound("User(s) that was(were) created between :" +
                        start + " and " + end)));
    }
}
