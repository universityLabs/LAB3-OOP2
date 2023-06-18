package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.HeroDto;
import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.domain.Hero;
import com.tech.zootech.security.exceptions.HeroNotFound;
import com.tech.zootech.security.repo.HeroRepo;
import com.tech.zootech.security.utils.mapper.HeroMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {HeroServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class HeroServiceImplTest {
    @MockBean
    private HeroMapper heroMapper;

    @MockBean
    private HeroRepo heroRepo;

    @Autowired
    private HeroServiceImpl heroServiceImpl;

    /**
     * Method under test: {@link HeroServiceImpl#getLastCreated(String)}
     */
    @Test
    public void testGetLastCreated() {
        when(heroRepo.findLastCreatedHeroInCity((String) any())).thenReturn(Optional.of(new Hero()));

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
        when(heroMapper.toDto((Hero) any())).thenReturn(heroDto);
        assertSame(heroDto, heroServiceImpl.getLastCreated("Oxford"));
        verify(heroRepo).findLastCreatedHeroInCity((String) any());
        verify(heroMapper).toDto((Hero) any());
    }

    /**
     * Method under test: {@link HeroServiceImpl#getLastCreated(String)}
     */
    @Test
    public void testGetLastCreated2() {
        when(heroRepo.findLastCreatedHeroInCity((String) any())).thenReturn(Optional.of(new Hero()));
        when(heroMapper.toDto((Hero) any())).thenThrow(new HeroNotFound("Not all who wander are lost"));
        assertThrows(HeroNotFound.class, () -> heroServiceImpl.getLastCreated("Oxford"));
        verify(heroRepo).findLastCreatedHeroInCity((String) any());
        verify(heroMapper).toDto((Hero) any());
    }

    /**
     * Method under test: {@link HeroServiceImpl#getLastCreated(String)}
     */
    @Test
    public void testGetLastCreated3() {
        when(heroRepo.findLastCreatedHeroInCity((String) any())).thenReturn(Optional.empty());

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
        when(heroMapper.toDto((Hero) any())).thenReturn(heroDto);
        assertThrows(HeroNotFound.class, () -> heroServiceImpl.getLastCreated("Oxford"));
        verify(heroRepo).findLastCreatedHeroInCity((String) any());
    }
}

