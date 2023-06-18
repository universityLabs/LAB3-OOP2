package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.domain.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper extends AbstractMapper<Planet, PlanetDto>{
    @Autowired
    public PlanetMapper() {
        super(Planet.class, PlanetDto.class);
    }
}
