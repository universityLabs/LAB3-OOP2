package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.domain.Planet;
import com.tech.zootech.security.repo.PlanetRepo;
import com.tech.zootech.security.service.implementations.PlanetServiceImpl;
import com.tech.zootech.security.utils.mapper.PlanetMapper;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class PlanetResourceTest {
    /**
     * Method under test: {@link PlanetResource#save(PlanetDto)}
     */
    @Test
    public void testSave2() {
        PlanetRepo planetRepo = mock(PlanetRepo.class);
        when(planetRepo.save((Planet) any())).thenReturn(new Planet());
        PlanetMapper planetMapper = mock(PlanetMapper.class);
        when(planetMapper.toDto((Planet) any())).thenReturn(new PlanetDto("Name"));
        when(planetMapper.toEntity((PlanetDto) any())).thenReturn(new Planet());
        PlanetResource planetResource = new PlanetResource(new PlanetServiceImpl(planetRepo, planetMapper));
        PlanetDto planetDto = new PlanetDto("Name");
        ResponseEntity<PlanetDto> actualSaveResult = planetResource.save(planetDto);
        assertEquals(planetDto, actualSaveResult.getBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(planetRepo).save((Planet) any());
        verify(planetMapper).toDto((Planet) any());
        verify(planetMapper).toEntity((PlanetDto) any());
    }


    /**
     * Method under test: {@link PlanetResource#get(Long)}
     */
    @Test
    public void testGet2() {
        PlanetRepo planetRepo = mock(PlanetRepo.class);
        when(planetRepo.findById((Long) any())).thenReturn(Optional.of(new Planet()));
        PlanetMapper planetMapper = mock(PlanetMapper.class);
        when(planetMapper.toDto((Planet) any())).thenReturn(new PlanetDto("Name"));
        ResponseEntity<PlanetDto> actualGetResult = (new PlanetResource(new PlanetServiceImpl(planetRepo, planetMapper)))
                .get(123L);
        assertTrue(actualGetResult.hasBody());
        assertTrue(actualGetResult.getHeaders().isEmpty());
        assertEquals(200, actualGetResult.getStatusCodeValue());
        verify(planetRepo).findById((Long) any());
        verify(planetMapper).toDto((Planet) any());
    }


    /**
     * Method under test: {@link PlanetResource#getByName(String)}
     */
    @Test
    public void testGetByName2() {
        PlanetRepo planetRepo = mock(PlanetRepo.class);
        when(planetRepo.findByName((String) any())).thenReturn(Optional.of(new Planet()));
        PlanetMapper planetMapper = mock(PlanetMapper.class);
        when(planetMapper.toDto((Planet) any())).thenReturn(new PlanetDto("Name"));
        ResponseEntity<PlanetDto> actualByName = (new PlanetResource(new PlanetServiceImpl(planetRepo, planetMapper)))
                .getByName("Name");
        assertTrue(actualByName.hasBody());
        assertTrue(actualByName.getHeaders().isEmpty());
        assertEquals(200, actualByName.getStatusCodeValue());
        verify(planetRepo).findByName((String) any());
        verify(planetMapper).toDto((Planet) any());
    }
}

