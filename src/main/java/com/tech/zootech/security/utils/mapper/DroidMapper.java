package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.repo.UnicornRepo;
import com.tech.zootech.security.domain.Droid;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DroidMapper extends AbstractMapper<Droid, com.tech.zootech.security.DTO.DroidDto> {

    private final ModelMapper modelMapper;
    private final UnicornRepo unicornRepository;

    @Autowired
    public DroidMapper(ModelMapper modelMapper, UnicornRepo unicornRepository) {
        super(Droid.class, com.tech.zootech.security.DTO.DroidDto.class);
        this.modelMapper = modelMapper;
        this.unicornRepository = unicornRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Droid.class, com.tech.zootech.security.DTO.DroidDto.class)
                .addMappings(m -> m.skip(com.tech.zootech.security.DTO.DroidDto::setUnicornId)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(com.tech.zootech.security.DTO.DroidDto.class, Droid.class)
                .addMappings(m -> m.skip(Droid::setUnicorn)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Droid source, com.tech.zootech.security.DTO.DroidDto destination) {
        destination.setUnicornId(getId(source));
    }

    private Long getId(Droid source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUnicorn().getId();
    }

    @Override
    void mapSpecificFields(com.tech.zootech.security.DTO.DroidDto source, Droid destination) {
        destination.setUnicorn(unicornRepository.findById(source.getUnicornId()).orElse(null));
    }
}
