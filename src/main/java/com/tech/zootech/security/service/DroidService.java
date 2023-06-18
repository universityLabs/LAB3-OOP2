package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.DTO.UnicornDto;

import java.util.List;
import java.util.Set;

public interface DroidService {
    DroidDto getDroidByUnicorn(UnicornDto unicornDto);
    List<DroidDto> getDroidByAlive(Boolean alive);
    Set<DroidDto> getDroidByName(String name);
}
