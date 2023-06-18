package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.ContinentDto;
import com.tech.zootech.security.DTO.PlanetDto;

import java.util.List;

public interface ContinentService {
    ContinentDto save(ContinentDto continentDto);
    void delete(ContinentDto continentDto);
    List<ContinentDto> getByPlanet(PlanetDto planet);
    ContinentDto getByName(String continentName);
}
