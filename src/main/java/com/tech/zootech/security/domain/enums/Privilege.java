package com.tech.zootech.security.domain.enums;

import lombok.Getter;

@Getter
public enum Privilege {
    HAS_ROLE_USER(false),
    HAS_ROLE_ADMIN(false);

    private final boolean modifiable;

    Privilege(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public static class Authority {
        public static final String OR = " or ";
        public static final String HAS_ROLE_USER = "hasAuthority(HAS_ROLE_USER)";
        public static final String HAS_ROLE_ADMIN = "hasAuthority(HAS_ROLE_ADMIN)";
    }
}
