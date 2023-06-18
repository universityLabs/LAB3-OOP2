package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.domain.Planet;
import com.tech.zootech.security.exceptions.PlanetException;
import com.tech.zootech.security.exceptions.PlanetNameNotFound;
import com.tech.zootech.security.repo.PlanetRepo;
import com.tech.zootech.security.utils.mapper.PlanetMapper;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {PlanetServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PlanetServiceImplTest {
    @MockBean
    private PlanetMapper planetMapper;

    @MockBean
    private PlanetRepo planetRepo;

    @Autowired
    private PlanetServiceImpl planetServiceImpl;

    /**
     * Method under test: {@link PlanetServiceImpl#save(PlanetDto)}
     */
    @Test
    public void testSave() {
        when(planetRepo.save((Planet) any())).thenReturn(new Planet());
        PlanetDto planetDto = new PlanetDto("Name");
        when(planetMapper.toDto((Planet) any())).thenReturn(planetDto);
        when(planetMapper.toEntity((PlanetDto) any())).thenReturn(new Planet());
        assertSame(planetDto, planetServiceImpl.save(new PlanetDto("Name")));
        verify(planetRepo).save((Planet) any());
        verify(planetMapper).toDto((Planet) any());
        verify(planetMapper).toEntity((PlanetDto) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#save(PlanetDto)}
     */
    @Test
    public void testSave2() {
        when(planetRepo.save((Planet) any())).thenReturn(new Planet());
        when(planetMapper.toDto((Planet) any())).thenThrow(new PlanetException("An error occurred"));
        when(planetMapper.toEntity((PlanetDto) any())).thenThrow(new PlanetException("An error occurred"));
        assertThrows(PlanetException.class, () -> planetServiceImpl.save(new PlanetDto("Name")));
        verify(planetMapper).toEntity((PlanetDto) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#get(Long)}
     */
    @Test
    public void testGet() {
        when(planetRepo.findById((Long) any())).thenReturn(Optional.of(new Planet()));
        PlanetDto planetDto = new PlanetDto("Name");
        when(planetMapper.toDto((Planet) any())).thenReturn(planetDto);
        assertSame(planetDto, planetServiceImpl.get(123L));
        verify(planetRepo).findById((Long) any());
        verify(planetMapper).toDto((Planet) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#get(Long)}
     */
    @Test
    public void testGet2() {
        when(planetRepo.findById((Long) any())).thenReturn(Optional.of(new Planet()));
        when(planetMapper.toDto((Planet) any())).thenThrow(new PlanetNameNotFound("Not all who wander are lost"));
        assertThrows(PlanetNameNotFound.class, () -> planetServiceImpl.get(123L));
        verify(planetRepo).findById((Long) any());
        verify(planetMapper).toDto((Planet) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#get(Long)}
     */
    @Test
    public void testGet3() {
        when(planetRepo.findById((Long) any())).thenReturn(Optional.empty());
        when(planetMapper.toDto((Planet) any())).thenReturn(new PlanetDto("Name"));
        assertThrows(PlanetException.class, () -> planetServiceImpl.get(123L));
        verify(planetRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName() {
        when(planetRepo.findByName((String) any())).thenReturn(Optional.of(new Planet()));
        PlanetDto planetDto = new PlanetDto("Name");
        when(planetMapper.toDto((Planet) any())).thenReturn(planetDto);
        assertSame(planetDto, planetServiceImpl.getByName("Name"));
        verify(planetRepo).findByName((String) any());
        verify(planetMapper).toDto((Planet) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName2() {
        when(planetRepo.findByName((String) any())).thenReturn(Optional.of(new Planet()));
        when(planetMapper.toDto((Planet) any())).thenThrow(new PlanetNameNotFound("Not all who wander are lost"));
        assertThrows(PlanetNameNotFound.class, () -> planetServiceImpl.getByName("Name"));
        verify(planetRepo).findByName((String) any());
        verify(planetMapper).toDto((Planet) any());
    }

    /**
     * Method under test: {@link PlanetServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName3() {
        when(planetRepo.findByName((String) any())).thenReturn(Optional.empty());
        when(planetMapper.toDto((Planet) any())).thenReturn(new PlanetDto("Name"));
        assertThrows(PlanetNameNotFound.class, () -> planetServiceImpl.getByName("Name"));
        verify(planetRepo).findByName((String) any());
    }
}

