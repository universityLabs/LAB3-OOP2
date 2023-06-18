package com.tech.zootech.security.DTO;

import lombok.Data;

/**
 * special dto that is not extending abstract dto class
 * because no need for id and created/updated fields.
 */
@Data
public class RoleToUserForm {
    private String username;
    private String roleName;
}
