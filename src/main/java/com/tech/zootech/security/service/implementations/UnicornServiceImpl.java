package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.enums.Color;
import com.tech.zootech.security.exceptions.UnicornColorNotFound;
import com.tech.zootech.security.exceptions.UnicornNameNotFound;
import com.tech.zootech.security.repo.UnicornRepo;
import com.tech.zootech.security.service.UnicornService;
import com.tech.zootech.security.utils.mapper.UnicornMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UnicornServiceImpl implements UnicornService {
    private final UnicornRepo repository;
    private final UnicornMapper mapper;

    @Override
    public UnicornDto save(UnicornDto dto) {
        log.info("Saving unicorn {} to db", dto.getName());
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public UnicornDto get(Long id) {
        var unicorn = repository.findById(id).orElseThrow();
        log.info("Getting unicorn {} from db", unicorn.getName());
        return mapper.toDto(unicorn);
    }

    @Override
    public UnicornDto getByName(String name) {
        var unicorn = repository.findByName(name)
                .orElseThrow(() -> new UnicornNameNotFound("Unicorn with name " + name + " not found"));
        log.info("Getting unicorn with name {} from bd", name);
        return mapper.toDto(unicorn);
    }

    @Override
    public List<UnicornDto> getByColor(Color color) {
        var unicorns = repository.findByColor(color)
                .orElseThrow(() -> new UnicornColorNotFound("Unicorn color"+color+" not found"));
        log.info("Getting unicorns by color {}", color);
        return unicorns.stream()
                .map(mapper::toDto)
                .toList();
    }
}
