package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.Unicorn;
import com.tech.zootech.security.domain.enums.Color;
import com.tech.zootech.security.exceptions.UnicornColorNotFound;
import com.tech.zootech.security.exceptions.UnicornNameNotFound;
import com.tech.zootech.security.repo.UnicornRepo;
import com.tech.zootech.security.utils.mapper.UnicornMapper;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {UnicornServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UnicornServiceImplTest {
    @MockBean
    private UnicornMapper unicornMapper;

    @MockBean
    private UnicornRepo unicornRepo;

    @Autowired
    private UnicornServiceImpl unicornServiceImpl;

    /**
     * Method under test: {@link UnicornServiceImpl#save(UnicornDto)}
     */
    @Test
    public void testSave() {
        when(unicornRepo.save((Unicorn) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        UnicornDto unicornDto = new UnicornDto();
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(unicornDto);
        when(unicornMapper.toEntity((UnicornDto) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        assertSame(unicornDto, unicornServiceImpl.save(new UnicornDto()));
        verify(unicornRepo).save((Unicorn) any());
        verify(unicornMapper).toDto((Unicorn) any());
        verify(unicornMapper).toEntity((UnicornDto) any());
    }

    /**
     * Method under test: {@link UnicornServiceImpl#save(UnicornDto)}
     */
    @Test
    public void testSave2() {
        when(unicornRepo.save((Unicorn) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        when(unicornMapper.toDto((Unicorn) any())).thenThrow(new UnicornNameNotFound("Not all who wander are lost"));
        when(unicornMapper.toEntity((UnicornDto) any()))
                .thenThrow(new UnicornNameNotFound("Not all who wander are lost"));
        assertThrows(UnicornNameNotFound.class, () -> unicornServiceImpl.save(new UnicornDto()));
        verify(unicornMapper).toEntity((UnicornDto) any());
    }


    /**
     * Method under test: {@link UnicornServiceImpl#get(Long)}
     */
    @Test
    public void testGet() {
        when(unicornRepo.findById((Long) any())).thenReturn(Optional.of(new Unicorn("Name", Color.BLACK)));
        UnicornDto unicornDto = new UnicornDto();
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(unicornDto);
        assertSame(unicornDto, unicornServiceImpl.get(123L));
        verify(unicornRepo).findById((Long) any());
        verify(unicornMapper).toDto((Unicorn) any());
    }

    /**
     * Method under test: {@link UnicornServiceImpl#get(Long)}
     */
    @Test
    public void testGet2() {
        when(unicornRepo.findById((Long) any())).thenReturn(Optional.of(new Unicorn("Name", Color.BLACK)));
        when(unicornMapper.toDto((Unicorn) any())).thenThrow(new UnicornColorNotFound("Not all who wander are lost"));
        assertThrows(UnicornColorNotFound.class, () -> unicornServiceImpl.get(123L));
        verify(unicornRepo).findById((Long) any());
        verify(unicornMapper).toDto((Unicorn) any());
    }

    /**
     * Method under test: {@link UnicornServiceImpl#get(Long)}
     */
    @Test
    public void testGet3() {
        Unicorn unicorn = mock(Unicorn.class);
        when(unicorn.getName()).thenThrow(new UnicornColorNotFound("Not all who wander are lost"));
        Optional<Unicorn> ofResult = Optional.of(unicorn);
        when(unicornRepo.findById((Long) any())).thenReturn(ofResult);
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(new UnicornDto());
        assertThrows(UnicornColorNotFound.class, () -> unicornServiceImpl.get(123L));
        verify(unicornRepo).findById((Long) any());
        verify(unicorn).getName();
    }


    /**
     * Method under test: {@link UnicornServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName() {
        when(unicornRepo.findByName((String) any())).thenReturn(Optional.of(new Unicorn("Name", Color.BLACK)));
        UnicornDto unicornDto = new UnicornDto();
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(unicornDto);
        assertSame(unicornDto, unicornServiceImpl.getByName("Name"));
        verify(unicornRepo).findByName((String) any());
        verify(unicornMapper).toDto((Unicorn) any());
    }

    /**
     * Method under test: {@link UnicornServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName2() {
        when(unicornRepo.findByName((String) any())).thenReturn(Optional.of(new Unicorn("Name", Color.BLACK)));
        when(unicornMapper.toDto((Unicorn) any())).thenThrow(new UnicornColorNotFound("Not all who wander are lost"));
        assertThrows(UnicornColorNotFound.class, () -> unicornServiceImpl.getByName("Name"));
        verify(unicornRepo).findByName((String) any());
        verify(unicornMapper).toDto((Unicorn) any());
    }

    /**
     * Method under test: {@link UnicornServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName3() {
        when(unicornRepo.findByName((String) any())).thenReturn(Optional.empty());
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(new UnicornDto());
        assertThrows(UnicornNameNotFound.class, () -> unicornServiceImpl.getByName("Name"));
        verify(unicornRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link UnicornServiceImpl#getByColor(Color)}
     */
    @Test
    public void testGetByColor() {
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(unicornServiceImpl.getByColor(Color.BLACK).isEmpty());
        verify(unicornRepo).findByColor((Color) any());
    }


    /**
     * Method under test: {@link UnicornServiceImpl#getByColor(Color)}
     */
    @Test
    public void testGetByColor3() {
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.empty());
        when(unicornMapper.toDto((Unicorn) any())).thenThrow(new UnicornColorNotFound("Not all who wander are lost"));
        assertThrows(UnicornColorNotFound.class, () -> unicornServiceImpl.getByColor(Color.BLACK));
        verify(unicornRepo).findByColor((Color) any());
    }
}

