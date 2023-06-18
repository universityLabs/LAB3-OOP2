package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.PlanetDto;
import com.tech.zootech.security.exceptions.PlanetException;
import com.tech.zootech.security.exceptions.PlanetNameNotFound;
import com.tech.zootech.security.repo.PlanetRepo;
import com.tech.zootech.security.service.PlanetService;
import com.tech.zootech.security.utils.mapper.PlanetMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepo repository;
    private final PlanetMapper mapper;

    @Override
    public PlanetDto save(PlanetDto dto) {
        log.info("saving planet to db");
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public PlanetDto get(Long id) {
        log.info("getting planet from db");
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new PlanetException(String.format("Unable to get by id %d", id))));
    }

    @Override
    public PlanetDto getByName(String name) {
        log.info("getting planet from db by name:" + name);
        return mapper.toDto(repository.findByName(name)
                .orElseThrow(() -> new PlanetNameNotFound("Planet name " + name + " not found.")));
    }
}
