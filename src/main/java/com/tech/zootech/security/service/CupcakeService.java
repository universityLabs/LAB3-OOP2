package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.CupcakeDto;
import com.tech.zootech.security.DTO.DroidDto;

import java.util.List;

public interface CupcakeService {
    CupcakeDto saveCupcake(CupcakeDto dto);
    CupcakeDto getCupcakeById(Long id);
    List<CupcakeDto> getByDroid(DroidDto droidDto);
}
