package com.tech.zootech.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UnicornDto extends AbstractDto {
    private String name;
    private List<DroidDto> droids;
    private Color color;
}
