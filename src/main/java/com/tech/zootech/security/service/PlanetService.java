package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.PlanetDto;

public interface PlanetService {
    PlanetDto save(PlanetDto dto);
    PlanetDto get(Long id);
    PlanetDto getByName(String name);
}
