package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.RoleDto;
import com.tech.zootech.security.DTO.UserDto;
import com.tech.zootech.security.domain.Role;
import com.tech.zootech.security.domain.User;
import com.tech.zootech.security.exceptions.UserNotFound;
import com.tech.zootech.security.repo.RoleRepo;
import com.tech.zootech.security.repo.UserRepo;
import com.tech.zootech.security.utils.mapper.RoleMapper;
import com.tech.zootech.security.utils.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {UserServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleMapper roleMapper;

    @MockBean
    private RoleRepo roleRepo;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;


    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(userRepo.findByUsername((String) any())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.loadUserByUsername("janedoe"));
        verify(userRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    public void testLoadUserByUsername3() throws UsernameNotFoundException {
        LocalDateTime created = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime updated = LocalDateTime.of(1, 1, 1, 1, 1);
        when(userRepo.findByUsername((String) any())).thenReturn(
                new User(123L, created, updated, "User found in the database: {}", "janedoe", "iloveyou", new HashSet<>()));
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    public void testLoadUserByUsername4() throws UsernameNotFoundException {
        User user = mock(User.class);
        when(user.getPassword()).thenThrow(new UserNotFound("Not all who wander are lost"));
        when(user.getUsername()).thenThrow(new UserNotFound("Not all who wander are lost"));
        when(user.getRoles()).thenThrow(new UserNotFound("Not all who wander are lost"));
        when(userRepo.findByUsername((String) any())).thenReturn(user);
        assertThrows(UserNotFound.class, () -> userServiceImpl.loadUserByUsername("janedoe"));
        verify(userRepo).findByUsername((String) any());
        verify(user).getRoles();
    }

    /**
     * Method under test: {@link UserServiceImpl#saveUser(UserDto)}
     */
    @Test
    public void testSaveUser() {
        when(userRepo.save((User) any())).thenReturn(new User());

        UserDto userDto = new UserDto();
        userDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setId(123L);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setUsername("janedoe");
        when(userMapper.toDto((User) any())).thenReturn(userDto);
        when(userMapper.toEntity((UserDto) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        UserDto userDto1 = new UserDto();
        userDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto1.setId(123L);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new ArrayList<>());
        userDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto1.setUsername("janedoe");
        assertSame(userDto, userServiceImpl.saveUser(userDto1));
        verify(userRepo).save((User) any());
        verify(userMapper).toDto((User) any());
        verify(userMapper).toEntity((UserDto) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveUser(UserDto)}
     */
    @Test
    public void testSaveUser2() {
        when(userRepo.save((User) any())).thenReturn(new User());

        UserDto userDto = new UserDto();
        userDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setId(123L);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setUsername("janedoe");
        when(userMapper.toDto((User) any())).thenReturn(userDto);
        when(userMapper.toEntity((UserDto) any())).thenReturn(new User());
        when(passwordEncoder.encode((CharSequence) any()))
                .thenThrow(new UsernameNotFoundException("Saving new user {} to db"));

        UserDto userDto1 = new UserDto();
        userDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto1.setId(123L);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new ArrayList<>());
        userDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto1.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.saveUser(userDto1));
        verify(userMapper).toEntity((UserDto) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }


    /**
     * Method under test: {@link UserServiceImpl#saveRole(RoleDto)}
     */
    @Test
    public void testSaveRole() {
        when(roleRepo.save((Role) any())).thenReturn(new Role());

        RoleDto roleDto = new RoleDto();
        roleDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        roleDto.setId(123L);
        roleDto.setName("Name");
        roleDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(roleMapper.toDto((Role) any())).thenReturn(roleDto);
        when(roleMapper.toEntity((RoleDto) any())).thenReturn(new Role());

        RoleDto roleDto1 = new RoleDto();
        roleDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        roleDto1.setId(123L);
        roleDto1.setName("Name");
        roleDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertSame(roleDto, userServiceImpl.saveRole(roleDto1));
        verify(roleRepo).save((Role) any());
        verify(roleMapper).toDto((Role) any());
        verify(roleMapper).toEntity((RoleDto) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveRole(RoleDto)}
     */
    @Test
    public void testSaveRole2() {
        when(roleRepo.save((Role) any())).thenReturn(new Role());
        when(roleMapper.toDto((Role) any())).thenThrow(new UsernameNotFoundException("Saving new {} to db"));
        when(roleMapper.toEntity((RoleDto) any())).thenReturn(new Role());

        RoleDto roleDto = new RoleDto();
        roleDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        roleDto.setId(123L);
        roleDto.setName("Name");
        roleDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.saveRole(roleDto));
        verify(roleRepo).save((Role) any());
        verify(roleMapper).toDto((Role) any());
        verify(roleMapper).toEntity((RoleDto) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addRoleToUser(String, String)}
     */
    @Test
    public void testAddRoleToUser() {
        when(userRepo.findByUsername((String) any())).thenReturn(new User());
        when(roleRepo.findByName((String) any())).thenReturn(new Role());
        userServiceImpl.addRoleToUser("janedoe", "Role Name");
        verify(userRepo).findByUsername((String) any());
        verify(roleRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addRoleToUser(String, String)}
     */
    @Test
    public void testAddRoleToUser2() {
        when(userRepo.findByUsername((String) any())).thenReturn(new User());
        when(roleRepo.findByName((String) any())).thenThrow(new UsernameNotFoundException("Adding role {} to user {}"));
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.addRoleToUser("janedoe", "Role Name"));
        verify(userRepo).findByUsername((String) any());
        verify(roleRepo).findByName((String) any());
    }


    /**
     * Method under test: {@link UserServiceImpl#getUserByUsername(String)}
     */
    @Test
    public void testGetUserByUsername() {
        when(userRepo.findByUsername((String) any())).thenReturn(new User());

        UserDto userDto = new UserDto();
        userDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setId(123L);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setUsername("janedoe");
        when(userMapper.toDto((User) any())).thenReturn(userDto);
        assertSame(userDto, userServiceImpl.getUserByUsername("janedoe"));
        verify(userRepo).findByUsername((String) any());
        verify(userMapper).toDto((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserByUsername(String)}
     */
    @Test
    public void testGetUserByUsername2() {
        when(userRepo.findByUsername((String) any())).thenReturn(new User());
        when(userMapper.toDto((User) any())).thenThrow(new UsernameNotFoundException("Getting user by username: {}"));
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.getUserByUsername("janedoe"));
        verify(userRepo).findByUsername((String) any());
        verify(userMapper).toDto((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers()}
     */
    @Test
    public void testGetUsers() {
        when(userRepo.findAllUsers()).thenReturn(new ArrayList<>());
        assertTrue(userServiceImpl.getUsers().isEmpty());
        verify(userRepo).findAllUsers();
    }

    /**
     * Method under test: {@link UserServiceImpl#findByName(String)}
     */
    @Test
    public void testFindByName() {
        when(userRepo.findByName((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(userServiceImpl.findByName("Name").isEmpty());
        verify(userRepo).findByName((String) any());
    }


    /**
     * Method under test: {@link UserServiceImpl#findByName(String)}
     */
    @Test
    public void testFindByName3() {
        when(userRepo.findByName((String) any())).thenReturn(Optional.empty());
        when(userMapper.toDto((User) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UserNotFound.class, () -> userServiceImpl.findByName("Name"));
        verify(userRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findUserByPassword(String)}
     */
    @Test
    public void testFindUserByPassword() {
        when(userRepo.findUserByPassword((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(userServiceImpl.findUserByPassword("iloveyou").isEmpty());
        verify(userRepo).findUserByPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findUserByPassword(String)}
     */
    @Test
    public void testFindUserByPassword3() {
        when(userRepo.findUserByPassword((String) any())).thenReturn(Optional.empty());
        when(userMapper.toDto((User) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UserNotFound.class, () -> userServiceImpl.findUserByPassword("iloveyou"));
        verify(userRepo).findUserByPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findByCreatedBetween(LocalDateTime, LocalDateTime)}
     */
    @Test
    public void testFindByCreatedBetween() {
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
        when(userMapper.toDto((User) any())).thenReturn(userDto);
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertSame(userDto, userServiceImpl.findByCreatedBetween(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(userRepo).findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any());
        verify(userMapper).toDto((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findByCreatedBetween(LocalDateTime, LocalDateTime)}
     */
    @Test
    public void testFindByCreatedBetween2() {
        when(userRepo.findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any()))
                .thenReturn(Optional.of(new User()));
        when(userMapper.toDto((User) any())).thenThrow(new UsernameNotFoundException("Msg"));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(UsernameNotFoundException.class,
                () -> userServiceImpl.findByCreatedBetween(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(userRepo).findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any());
        verify(userMapper).toDto((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findByCreatedBetween(LocalDateTime, LocalDateTime)}
     */
    @Test
    public void testFindByCreatedBetween3() {
        when(userRepo.findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any())).thenReturn(Optional.empty());

        UserDto userDto = new UserDto();
        userDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setId(123L);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new ArrayList<>());
        userDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setUsername("janedoe");
        when(userMapper.toDto((User) any())).thenReturn(userDto);
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(UserNotFound.class,
                () -> userServiceImpl.findByCreatedBetween(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(userRepo).findByCreatedBetween((LocalDateTime) any(), (LocalDateTime) any());
    }
}

