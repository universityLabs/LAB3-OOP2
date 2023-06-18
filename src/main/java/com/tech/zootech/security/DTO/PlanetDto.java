package com.tech.zootech.security.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PlanetDto extends AbstractDto {
    private String name;
    private List<ContinentDto> continents;

    public PlanetDto(String name) {
        this.name = name;
    }
}
