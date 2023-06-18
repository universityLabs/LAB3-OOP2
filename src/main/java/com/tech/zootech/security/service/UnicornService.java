package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.enums.Color;

import java.util.List;

public interface UnicornService {
    UnicornDto save(UnicornDto dto);
    UnicornDto get(Long id);
    UnicornDto getByName(String name);
    List<UnicornDto> getByColor(Color color);
}
