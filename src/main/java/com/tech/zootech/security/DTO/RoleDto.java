package com.tech.zootech.security.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends AbstractDto {
    private String name;
}
