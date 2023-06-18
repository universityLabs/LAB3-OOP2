package com.tech.zootech.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DroidDto extends AbstractDto {
    private String name;
    private List<CupcakeDto> cupcakes;
    private Long unicornId;
    private Boolean alive;
}
