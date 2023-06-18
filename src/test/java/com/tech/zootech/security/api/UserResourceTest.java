package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.zootech.security.DTO.RoleDto;
import com.tech.zootech.security.DTO.RoleToUserForm;
import com.tech.zootech.security.DTO.UserDto;
import com.tech.zootech.security.domain.Role;
import com.tech.zootech.security.domain.User;
import com.tech.zootech.security.repo.RoleRepo;
import com.tech.zootech.security.repo.UserRepo;
import com.tech.zootech.security.service.UserService;
import com.tech.zootech.security.service.implementations.UserServiceImpl;
import com.tech.zootech.security.utils.contracts.RefreshTokenUtil;
import com.tech.zootech.security.utils.mapper.RoleMapper;
import com.tech.zootech.security.utils.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserResource.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserResourceTest {
    @MockBean
    private RefreshTokenUtil refreshTokenUtil;

    @Autowired
    private UserResource userResource;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserResource#getUsers()}
     */
    @Test
    public void testGetUsers() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findAllUsers()).thenReturn(new ArrayList<>());
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        ResponseEntity<List<UserDto>> actualUsers = (new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                mock(RefreshTokenUtil.class))).getUsers(any());
        assertTrue(actualUsers.hasBody());
        assertEquals(200, actualUsers.getStatusCodeValue());
        assertTrue(actualUsers.getHeaders().isEmpty());
        verify(userRepo).findAllUsers();
    }


    /**
     * Method under test: {@link UserResource#getUserByName(String)}
     */
    @Test
    public void testGetUserByName2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername((String) any())).thenReturn(null);
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        ResponseEntity<UserDto> actualUserByName = (new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                mock(RefreshTokenUtil.class))).getUserByName("janedoe");
        assertNull(actualUserByName.getBody());
        assertEquals(200, actualUserByName.getStatusCodeValue());
        assertTrue(actualUserByName.getHeaders().isEmpty());
        verify(userRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserResource#addRoleToUser(RoleToUserForm)}
     */
    @Test
    public void testAddRoleToUser() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByUsername((String) any())).thenReturn(new User());
        RoleRepo roleRepo = mock(RoleRepo.class);
        when(roleRepo.findByName((String) any())).thenReturn(new Role());
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        UserResource userResource = new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                mock(RefreshTokenUtil.class));

        RoleToUserForm roleToUserForm = new RoleToUserForm();
        roleToUserForm.setRoleName("Role Name");
        roleToUserForm.setUsername("janedoe");
        ResponseEntity<Void> actualAddRoleToUserResult = userResource.addRoleToUser(roleToUserForm);
        assertNull(actualAddRoleToUserResult.getBody());
        assertEquals(200, actualAddRoleToUserResult.getStatusCodeValue());
        assertTrue(actualAddRoleToUserResult.getHeaders().isEmpty());
        verify(userRepo).findByUsername((String) any());
        verify(roleRepo).findByName((String) any());
    }


    /**
     * Method under test: {@link UserResource#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    public void testRefreshToken() throws IOException {
        RefreshTokenUtil refreshTokenUtil = mock(RefreshTokenUtil.class);
        doNothing().when(refreshTokenUtil).refresh((HttpServletRequest) any(), (HttpServletResponse) any());
        UserRepo userRepo = mock(UserRepo.class);
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        UserResource userResource = new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                refreshTokenUtil);
        MockHttpServletRequest request = new MockHttpServletRequest();
        ResponseEntity<Void> actualRefreshTokenResult = userResource.refreshToken(request, new Response());
        assertNull(actualRefreshTokenResult.getBody());
        assertEquals(200, actualRefreshTokenResult.getStatusCodeValue());
        assertTrue(actualRefreshTokenResult.getHeaders().isEmpty());
        verify(refreshTokenUtil).refresh((HttpServletRequest) any(), (HttpServletResponse) any());
    }

    /**
     * Method under test: {@link UserResource#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    public void testRefreshToken2() throws IOException {
        RefreshTokenUtil refreshTokenUtil = mock(RefreshTokenUtil.class);
        doThrow(new IOException()).when(refreshTokenUtil)
                .refresh((HttpServletRequest) any(), (HttpServletResponse) any());
        UserRepo userRepo = mock(UserRepo.class);
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        UserResource userResource = new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                refreshTokenUtil);
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertThrows(IOException.class, () -> userResource.refreshToken(request, new Response()));
        verify(refreshTokenUtil).refresh((HttpServletRequest) any(), (HttpServletResponse) any());
    }

    /**
     * Method under test: {@link UserResource#findByName(String)}
     */
    @Test
    public void testFindByName() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByName((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        ResponseEntity<List<UserDto>> actualFindByNameResult = (new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                mock(RefreshTokenUtil.class))).findByName("Name");
        assertTrue(actualFindByNameResult.hasBody());
        assertEquals(200, actualFindByNameResult.getStatusCodeValue());
        assertTrue(actualFindByNameResult.getHeaders().isEmpty());
        verify(userRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link UserResource#findUserByPassword(String)}
     */
    @Test
    public void testFindUserByPassword() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findUserByPassword((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        RoleRepo roleRepo = mock(RoleRepo.class);
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        ResponseEntity<List<UserDto>> actualFindUserByPasswordResult = (new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                mock(RefreshTokenUtil.class))).findUserByPassword("iloveyou");
        assertTrue(actualFindUserByPasswordResult.hasBody());
        assertEquals(200, actualFindUserByPasswordResult.getStatusCodeValue());
        assertTrue(actualFindUserByPasswordResult.getHeaders().isEmpty());
        verify(userRepo).findUserByPassword((String) any());
    }


    /**
     * Method under test: {@link UserResource#findByCreatedBetween(LocalDateTime, LocalDateTime)}
     */
    @Test
    public void testFindByCreatedBetween2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any()))
                .thenReturn(Optional.of(new User()));

        UserDto userDto = new UserDto();
        userDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setId(123L);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setUsername("janedoe");
        UserMapper userMapper = mock(UserMapper.class);
        when(userMapper.toDto((User) any())).thenReturn(userDto);
        RoleRepo roleRepo = mock(RoleRepo.class);
        RoleMapper roleMapper = new RoleMapper();
        UserResource userResource = new UserResource(
                new UserServiceImpl(userRepo, roleRepo, userMapper, roleMapper, new BCryptPasswordEncoder()),
                mock(RefreshTokenUtil.class));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        ResponseEntity<UserDto> actualFindByCreatedBetweenResult = userResource.findByCreatedBetween(start,
                LocalDateTime.of(1, 1, 1, 1, 1));
        assertTrue(actualFindByCreatedBetweenResult.hasBody());
        assertTrue(actualFindByCreatedBetweenResult.getHeaders().isEmpty());
        assertEquals(200, actualFindByCreatedBetweenResult.getStatusCodeValue());
        verify(userRepo).findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any());
        verify(userMapper).toDto((User) any());
    }
}

