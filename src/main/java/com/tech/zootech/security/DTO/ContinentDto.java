package com.tech.zootech.security.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContinentDto extends AbstractDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PlanetDto planet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HeroDto> heroes;
}
