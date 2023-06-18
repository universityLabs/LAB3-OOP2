package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.HeroDto;
import com.tech.zootech.security.exceptions.HeroNotFound;
import com.tech.zootech.security.repo.HeroRepo;
import com.tech.zootech.security.service.HeroService;
import com.tech.zootech.security.utils.mapper.HeroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {
    private final HeroRepo heroRepo;
    private final HeroMapper mapper;

    @Override
    public HeroDto getLastCreated(String city) {
        final var hero = heroRepo.findLastCreatedHeroInCity(city)
                .orElseThrow(() -> new HeroNotFound("Hero not found!"));
        return mapper.toDto(hero);
    }
}
