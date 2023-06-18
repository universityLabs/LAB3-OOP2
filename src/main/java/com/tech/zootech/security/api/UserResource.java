package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.RoleDto;
import com.tech.zootech.security.DTO.RoleToUserForm;
import com.tech.zootech.security.DTO.UserDto;
import com.tech.zootech.security.domain.enums.Privilege;
import com.tech.zootech.security.domain.security.AuthenticationUser;
import com.tech.zootech.security.service.UserService;
import com.tech.zootech.security.utils.contracts.RefreshTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users/v1")
public class UserResource {
    private final UserService userService;
    private final RefreshTokenUtil refreshTokenUtil;

    @GetMapping("/allUsers")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<List<UserDto>> getUsers(@AuthenticationPrincipal AuthenticationUser authenticationUser) {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDto> getUserByName(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @PostMapping("/user/save")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        var uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(userDto));
    }

    @PostMapping("/role/save")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        var uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(roleDto));
    }

    @PostMapping("/role/addToUser")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<Void> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<Void> refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        refreshTokenUtil.refresh(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByName/{name}")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<List<UserDto>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping("/getByPassword/{password}")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<List<UserDto>> findUserByPassword(@PathVariable String password) {
        return ResponseEntity.ok(userService.findUserByPassword(password));
    }

    @GetMapping("/getByDatesBetween")
    @PreAuthorize(Privilege.Authority.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDto> findByCreatedBetween(@RequestParam LocalDateTime start,
                                                        @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(userService.findByCreatedBetween(start, end));
    }
}
