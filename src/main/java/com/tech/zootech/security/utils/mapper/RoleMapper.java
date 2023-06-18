package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.DTO.RoleDto;
import com.tech.zootech.security.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDto>{
    @Autowired
    public RoleMapper() {
        super(Role.class, RoleDto.class);
    }
}
