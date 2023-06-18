package com.tech.zootech.security.utils.mapper;


import com.tech.zootech.security.DTO.AbstractDto;
import com.tech.zootech.security.domain.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
