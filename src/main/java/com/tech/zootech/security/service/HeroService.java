package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.HeroDto;

public interface HeroService {
    HeroDto getLastCreated(String city);
}
