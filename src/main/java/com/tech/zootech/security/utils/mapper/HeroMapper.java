package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.DTO.HeroDto;
import com.tech.zootech.security.domain.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeroMapper extends AbstractMapper<Hero, HeroDto>{
    @Autowired
    HeroMapper() {
        super(Hero.class, HeroDto.class);
    }
}
