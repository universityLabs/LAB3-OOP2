package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.HeroDto;
import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.service.HeroService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class HeroResourceTest {
    /**
     * Method under test: {@link HeroResource#getLastHero(String)}
     */
    @Test
    public void testGetLastHero() {
        ContinentDto continentDto = new ContinentDto();
        continentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        continentDto.setHeroes(new ArrayList<>());
        continentDto.setId(123L);
        continentDto.setName("Name");
        continentDto.setPlanet(new PlanetDto("Name"));
        continentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        HeroDto heroDto = new HeroDto();
        heroDto.setCity("Oxford");
        heroDto.setContinent(continentDto);
        heroDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        heroDto.setDateOfBirth(LocalDateTime.of(1, 1, 1, 1, 1));
        heroDto.setFirstName("Jane");
        heroDto.setId(123L);
        heroDto.setLastName("Doe");
        heroDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        HeroService heroService = mock(HeroService.class);
        when(heroService.getLastCreated((String) any())).thenReturn(heroDto);
        ResponseEntity<HeroDto> actualLastHero = (new HeroResource(heroService)).getLastHero("Oxford");
        assertTrue(actualLastHero.hasBody());
        assertTrue(actualLastHero.getHeaders().isEmpty());
        assertEquals(200, actualLastHero.getStatusCodeValue());
        verify(heroService).getLastCreated((String) any());
    }
}

