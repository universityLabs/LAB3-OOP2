package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.domain.Continent;
import com.tech.zootech.security.domain.Planet;
import com.tech.zootech.security.exceptions.ContinentNameNotFound;
import com.tech.zootech.security.exceptions.ContinentNotFoundException;
import com.tech.zootech.security.repo.ContinentRepo;
import com.tech.zootech.security.utils.mapper.ContinentMapper;
import com.tech.zootech.security.utils.mapper.PlanetMapper;

import java.time.LocalDateTime;
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

@ContextConfiguration(classes = {ContinentServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ContinentServiceImplTest {
    @MockBean
    private ContinentMapper continentMapper;

    @MockBean
    private ContinentRepo continentRepo;

    @Autowired
    private ContinentServiceImpl continentServiceImpl;

    @MockBean
    private PlanetMapper planetMapper;

    /**
     * Method under test: {@link ContinentServiceImpl#save(ContinentDto)}
     */
    @Test
    public void testSave() {
        when(continentRepo.save((Continent) any())).thenReturn(new Continent());

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(continentMapper.toDto((Continent) any())).thenReturn(continentDto);
        when(continentMapper.toEntity((ContinentDto) any())).thenReturn(new Continent());

        ContinentDto continentDto1 = new ContinentDto();
        continentDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto1.setHeroes(new ArrayList<>());
        continentDto1.setId(123L);
        continentDto1.setName("Name");
        continentDto1.setPlanet(new PlanetDto("Name"));
        continentDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertSame(continentDto, continentServiceImpl.save(continentDto1));
        verify(continentRepo).save((Continent) any());
        verify(continentMapper).toDto((Continent) any());
        verify(continentMapper).toEntity((ContinentDto) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#save(ContinentDto)}
     */
    @Test
    public void testSave2() {
        when(continentRepo.save((Continent) any())).thenReturn(new Continent());
        when(continentMapper.toDto((Continent) any())).thenThrow(new ContinentNotFoundException("An error occurred"));
        when(continentMapper.toEntity((ContinentDto) any()))
                .thenThrow(new ContinentNotFoundException("An error occurred"));

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertThrows(ContinentNotFoundException.class, () -> continentServiceImpl.save(continentDto));
        verify(continentMapper).toEntity((ContinentDto) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#delete(ContinentDto)}
     */
    @Test
    public void testDelete() {
        doNothing().when(continentRepo).delete((Continent) any());
        when(continentMapper.toEntity((ContinentDto) any())).thenReturn(new Continent());

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentServiceImpl.delete(continentDto);
        verify(continentRepo).delete((Continent) any());
        verify(continentMapper).toEntity((ContinentDto) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#delete(ContinentDto)}
     */
    @Test
    public void testDelete2() {
        doNothing().when(continentRepo).delete((Continent) any());
        when(continentMapper.toEntity((ContinentDto) any()))
                .thenThrow(new ContinentNameNotFound("Not all who wander are lost"));

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertThrows(ContinentNameNotFound.class, () -> continentServiceImpl.delete(continentDto));
        verify(continentMapper).toEntity((ContinentDto) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#getByPlanet(PlanetDto)}
     */
    @Test
    public void testGetByPlanet() {
        when(continentRepo.findByPlanet((Planet) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(planetMapper.toEntity((PlanetDto) any())).thenReturn(new Planet());
        assertTrue(continentServiceImpl.getByPlanet(new PlanetDto("Name")).isEmpty());
        verify(continentRepo).findByPlanet((Planet) any());
        verify(planetMapper).toEntity((PlanetDto) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#getByPlanet(PlanetDto)}
     */
    @Test
    public void testGetByPlanet2() {
        when(continentRepo.findByPlanet((Planet) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(planetMapper.toEntity((PlanetDto) any()))
                .thenThrow(new ContinentNameNotFound("Not all who wander are lost"));
        assertThrows(ContinentNameNotFound.class, () -> continentServiceImpl.getByPlanet(new PlanetDto("Name")));
        verify(planetMapper).toEntity((PlanetDto) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#getByPlanet(PlanetDto)}
     */
    @Test
    public void testGetByPlanet4() {
        when(continentRepo.findByPlanet((Planet) any())).thenReturn(Optional.empty());
        when(planetMapper.toEntity((PlanetDto) any())).thenReturn(new Planet());
        when(continentMapper.toDto((Continent) any()))
                .thenThrow(new ContinentNameNotFound("Not all who wander are lost"));
        assertThrows(ContinentNotFoundException.class, () -> continentServiceImpl.getByPlanet(new PlanetDto("Name")));
        verify(continentRepo).findByPlanet((Planet) any());
        verify(planetMapper).toEntity((PlanetDto) any());
    }


    /**
     * Method under test: {@link ContinentServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName() {
        when(continentRepo.findByName((String) any())).thenReturn(Optional.of(new Continent()));

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(continentMapper.toDto((Continent) any())).thenReturn(continentDto);
        assertSame(continentDto, continentServiceImpl.getByName("Continent Name"));
        verify(continentRepo).findByName((String) any());
        verify(continentMapper).toDto((Continent) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName2() {
        when(continentRepo.findByName((String) any())).thenReturn(Optional.of(new Continent()));
        when(continentMapper.toDto((Continent) any()))
                .thenThrow(new ContinentNameNotFound("Not all who wander are lost"));
        assertThrows(ContinentNameNotFound.class, () -> continentServiceImpl.getByName("Continent Name"));
        verify(continentRepo).findByName((String) any());
        verify(continentMapper).toDto((Continent) any());
    }

    /**
     * Method under test: {@link ContinentServiceImpl#getByName(String)}
     */
    @Test
    public void testGetByName3() {
        when(continentRepo.findByName((String) any())).thenReturn(Optional.empty());

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(continentMapper.toDto((Continent) any())).thenReturn(continentDto);
        assertThrows(ContinentNameNotFound.class, () -> continentServiceImpl.getByName("Continent Name"));
        verify(continentRepo).findByName((String) any());
    }
}

