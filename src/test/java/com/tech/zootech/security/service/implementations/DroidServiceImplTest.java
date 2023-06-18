package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.domain.Unicorn;
import com.tech.zootech.security.domain.enums.Color;
import com.tech.zootech.security.exceptions.DroidNotFound;
import com.tech.zootech.security.repo.DroidRepo;
import com.tech.zootech.security.utils.mapper.DroidMapper;
import com.tech.zootech.security.utils.mapper.UnicornMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {DroidServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DroidServiceImplTest {
    @MockBean
    private DroidMapper droidMapper;

    @MockBean
    private DroidRepo droidRepo;

    @Autowired
    private DroidServiceImpl droidServiceImpl;

    @MockBean
    private UnicornMapper unicornMapper;

    /**
     * Method under test: {@link DroidServiceImpl#getDroidByUnicorn(UnicornDto)}
     */
    @Test
    public void testGetDroidByUnicorn() {
        DroidDto droidDto = new DroidDto();
        when(droidMapper.toDto((Droid) any())).thenReturn(droidDto);
        when(droidRepo.findDroidByUnicorn((Unicorn) any())).thenReturn(Optional.of(new Droid()));
        when(unicornMapper.toEntity((UnicornDto) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        assertSame(droidDto, droidServiceImpl.getDroidByUnicorn(new UnicornDto()));
        verify(droidMapper).toDto((Droid) any());
        verify(droidRepo).findDroidByUnicorn((Unicorn) any());
        verify(unicornMapper).toEntity((UnicornDto) any());
    }

    /**
     * Method under test: {@link DroidServiceImpl#getDroidByUnicorn(UnicornDto)}
     */
    @Test
    public void testGetDroidByUnicorn2() {
        when(droidMapper.toDto((Droid) any())).thenReturn(new DroidDto());
        when(droidRepo.findDroidByUnicorn((Unicorn) any())).thenReturn(Optional.of(new Droid()));
        when(unicornMapper.toEntity((UnicornDto) any())).thenThrow(new DroidNotFound("Not all who wander are lost"));
        assertThrows(DroidNotFound.class, () -> droidServiceImpl.getDroidByUnicorn(new UnicornDto()));
        verify(unicornMapper).toEntity((UnicornDto) any());
    }

    /**
     * Method under test: {@link DroidServiceImpl#getDroidByUnicorn(UnicornDto)}
     */
    @Test
    public void testGetDroidByUnicorn3() {
        when(droidMapper.toDto((Droid) any())).thenReturn(new DroidDto());
        when(droidRepo.findDroidByUnicorn((Unicorn) any())).thenReturn(Optional.empty());
        when(unicornMapper.toEntity((UnicornDto) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        assertThrows(DroidNotFound.class, () -> droidServiceImpl.getDroidByUnicorn(new UnicornDto()));
        verify(droidRepo).findDroidByUnicorn((Unicorn) any());
        verify(unicornMapper).toEntity((UnicornDto) any());
    }

    /**
     * Method under test: {@link DroidServiceImpl#getDroidByAlive(Boolean)}
     */
    @Test
    public void testGetDroidByAlive() {
        when(droidRepo.findDroidByAlive((Boolean) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(droidServiceImpl.getDroidByAlive(true).isEmpty());
        verify(droidRepo).findDroidByAlive((Boolean) any());
    }


    /**
     * Method under test: {@link DroidServiceImpl#getDroidByAlive(Boolean)}
     */
    @Test
    public void testGetDroidByAlive3() {
        when(droidMapper.toDto((Droid) any())).thenThrow(new DroidNotFound("Not all who wander are lost"));
        when(droidRepo.findDroidByAlive((Boolean) any())).thenReturn(Optional.empty());
        assertThrows(DroidNotFound.class, () -> droidServiceImpl.getDroidByAlive(true));
        verify(droidRepo).findDroidByAlive((Boolean) any());
    }

    /**
     * Method under test: {@link DroidServiceImpl#getDroidByName(String)}
     */
    @Test
    public void testGetDroidByName() {
        when(droidRepo.findDroidByName((String) any())).thenReturn(Optional.of(new HashSet<>()));
        assertTrue(droidServiceImpl.getDroidByName("Name").isEmpty());
        verify(droidRepo).findDroidByName((String) any());
    }


    /**
     * Method under test: {@link DroidServiceImpl#getDroidByName(String)}
     */
    @Test
    public void testGetDroidByName3() {
        when(droidMapper.toDto((Droid) any())).thenThrow(new DroidNotFound("Not all who wander are lost"));
        when(droidRepo.findDroidByName((String) any())).thenReturn(Optional.empty());
        assertThrows(DroidNotFound.class, () -> droidServiceImpl.getDroidByName("Name"));
        verify(droidRepo).findDroidByName((String) any());
    }
}

