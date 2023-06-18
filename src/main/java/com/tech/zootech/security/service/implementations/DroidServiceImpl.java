package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.exceptions.DroidNotFound;
import com.tech.zootech.security.repo.DroidRepo;
import com.tech.zootech.security.service.DroidService;
import com.tech.zootech.security.utils.mapper.DroidMapper;
import com.tech.zootech.security.utils.mapper.UnicornMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service("droidDefaultService")
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DroidServiceImpl implements DroidService {
    private final DroidMapper droidMapper;
    private final DroidRepo droidRepo;
    private final UnicornMapper unicornMapper;

    @Override
    public DroidDto getDroidByUnicorn(UnicornDto unicornDto) {
        log.info("getting droid by unicornDto");
        var unicorn = unicornMapper.toEntity(unicornDto);
        var droid = droidRepo.findDroidByUnicorn(unicorn).orElseThrow(() ->
                new DroidNotFound("droid not found by unicorn: " + unicornDto));
        return droidMapper.toDto(droid);
    }

    @Override
    public List<DroidDto> getDroidByAlive(Boolean alive) {
        log.info("getting all droids by bool value: " + alive);
        var droids = droidRepo.findDroidByAlive(alive).orElseThrow(() ->
                new DroidNotFound("droids not found"));
        return droids.stream()
                .map(droidMapper::toDto)
                .toList();
    }

    @Override
    public Set<DroidDto> getDroidByName(String name) {
        var droids = droidRepo.findDroidByName(name).orElseThrow(() ->
                new DroidNotFound("no droid with this name"));
        return droids.stream()
                .map(droidMapper::toDto)
                .collect(toSet());
    }
}
