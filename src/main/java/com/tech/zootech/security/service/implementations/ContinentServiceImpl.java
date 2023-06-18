package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.exceptions.ContinentNameNotFound;
import com.tech.zootech.security.exceptions.ContinentNotFoundException;
import com.tech.zootech.security.repo.ContinentRepo;
import com.tech.zootech.security.service.ContinentService;
import com.tech.zootech.security.utils.mapper.ContinentMapper;
import com.tech.zootech.security.utils.mapper.PlanetMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ContinentServiceImpl implements ContinentService {
    private final ContinentRepo continentRepo;
    private final PlanetMapper planetMapper;
    private final ContinentMapper continentMapper;

    @Override
    public ContinentDto save(ContinentDto continentDto) {
        var continent = continentMapper.toEntity(continentDto);
        log.info("Saving continent to db");
        continentRepo.save(continent);
        return continentMapper.toDto(continent);
    }

    @Override
    public void delete(ContinentDto continentDto) {
        log.info("Deleting continent with name: {} from db", continentDto.getName());
        var continent = continentMapper.toEntity(continentDto);
        continentRepo.delete(continent);
    }

    @Override
    public List<ContinentDto> getByPlanet(PlanetDto planetDto) {
        log.info("Getting continents by planet: {}", planetDto.getName());
        var planet = planetMapper.toEntity(planetDto);
        var continents = continentRepo.findByPlanet(planet)
                .orElseThrow(() -> new ContinentNotFoundException(
                        "Continents on planet" + planetDto.getName()+ " not found"
                ));
        return continents.stream()
                .map(continentMapper::toDto)
                .toList();
    }

    @Override
    public ContinentDto getByName(String continentName) {
        log.info("Getting continent by name: {}", continentName);
        var continent = continentRepo.findByName(continentName)
                .orElseThrow(() -> new ContinentNameNotFound(
                        "Continent with name " + continentName + " not found"
                ));
        return continentMapper.toDto(continent);
    }
}
