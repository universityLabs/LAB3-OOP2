package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.domain.Continent;
import com.tech.zootech.security.domain.Planet;
import com.tech.zootech.security.repo.ContinentRepo;
import com.tech.zootech.security.service.implementations.ContinentServiceImpl;
import com.tech.zootech.security.utils.mapper.ContinentMapper;
import com.tech.zootech.security.utils.mapper.PlanetMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class ContinentResourceTest {

    /**
     * Method under test: {@link ContinentResource#saveContinent(ContinentDto)}
     */
    @Test
    public void testSaveContinent2() {
        ContinentRepo continentRepo = mock(ContinentRepo.class);
        when(continentRepo.save((Continent) any())).thenReturn(new Continent());

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ContinentMapper continentMapper = mock(ContinentMapper.class);
        when(continentMapper.toDto((Continent) any())).thenReturn(continentDto);
        when(continentMapper.toEntity((ContinentDto) any())).thenReturn(new Continent());
        ContinentResource continentResource = new ContinentResource(
                new ContinentServiceImpl(continentRepo, new PlanetMapper(), continentMapper));

        ContinentDto continentDto1 = new ContinentDto();
        continentDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto1.setHeroes(new ArrayList<>());
        continentDto1.setId(123L);
        continentDto1.setName("Name");
        continentDto1.setPlanet(new PlanetDto("Name"));
        continentDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ResponseEntity<ContinentDto> actualSaveContinentResult = continentResource.saveContinent(continentDto1);
        assertEquals(continentDto1, actualSaveContinentResult.getBody());
        assertTrue(actualSaveContinentResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveContinentResult.getStatusCodeValue());
        verify(continentRepo).save((Continent) any());
        verify(continentMapper).toDto((Continent) any());
        verify(continentMapper).toEntity((ContinentDto) any());
    }


    /**
     * Method under test: {@link ContinentResource#deleteContinent(ContinentDto)}
     */
    @Test
    public void testDeleteContinent2() {
        ContinentRepo continentRepo = mock(ContinentRepo.class);
        doNothing().when(continentRepo).delete((Continent) any());
        ContinentMapper continentMapper = mock(ContinentMapper.class);
        when(continentMapper.toEntity((ContinentDto) any())).thenReturn(new Continent());
        ContinentResource continentResource = new ContinentResource(
                new ContinentServiceImpl(continentRepo, new PlanetMapper(), continentMapper));

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ResponseEntity<Void> actualDeleteContinentResult = continentResource.deleteContinent(continentDto);
        assertNull(actualDeleteContinentResult.getBody());
        assertEquals(200, actualDeleteContinentResult.getStatusCodeValue());
        assertTrue(actualDeleteContinentResult.getHeaders().isEmpty());
        verify(continentRepo).delete((Continent) any());
        verify(continentMapper).toEntity((ContinentDto) any());
    }


    /**
     * Method under test: {@link ContinentResource#getContinentsByPlanet(PlanetDto)}
     */
    @Test
    public void testGetContinentsByPlanet2() {
        ContinentRepo continentRepo = mock(ContinentRepo.class);
        when(continentRepo.findByPlanet((Planet) any())).thenReturn(Optional.of(new ArrayList<>()));
        PlanetMapper planetMapper = mock(PlanetMapper.class);
        when(planetMapper.toEntity((PlanetDto) any())).thenReturn(new Planet());
        ContinentResource continentResource = new ContinentResource(
                new ContinentServiceImpl(continentRepo, planetMapper, new ContinentMapper()));
        ResponseEntity<List<ContinentDto>> actualContinentsByPlanet = continentResource
                .getContinentsByPlanet(new PlanetDto("Name"));
        assertTrue(actualContinentsByPlanet.hasBody());
        assertEquals(200, actualContinentsByPlanet.getStatusCodeValue());
        assertTrue(actualContinentsByPlanet.getHeaders().isEmpty());
        verify(continentRepo).findByPlanet((Planet) any());
        verify(planetMapper).toEntity((PlanetDto) any());
    }


    /**
     * Method under test: {@link ContinentResource#getContinentByName(String)}
     */
    @Test
    public void testGetContinentByName2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ContinentRepo continentRepo = mock(ContinentRepo.class);
        when(continentRepo.findByName((String) any())).thenReturn(Optional.of(new Continent()));

        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ContinentMapper continentMapper = mock(ContinentMapper.class);
        when(continentMapper.toDto((Continent) any())).thenReturn(continentDto);
        ResponseEntity<ContinentDto> actualContinentByName = (new ContinentResource(
                new ContinentServiceImpl(continentRepo, new PlanetMapper(), continentMapper))).getContinentByName("Name");
        assertTrue(actualContinentByName.hasBody());
        assertTrue(actualContinentByName.getHeaders().isEmpty());
        assertEquals(200, actualContinentByName.getStatusCodeValue());
        verify(continentRepo).findByName((String) any());
        verify(continentMapper).toDto((Continent) any());
    }
}

