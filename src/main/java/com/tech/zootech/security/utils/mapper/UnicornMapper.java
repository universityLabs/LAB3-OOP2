package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.Unicorn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnicornMapper extends AbstractMapper<Unicorn, UnicornDto> {
    @Autowired
    public UnicornMapper() {
        super(Unicorn.class, UnicornDto.class);
    }
}
