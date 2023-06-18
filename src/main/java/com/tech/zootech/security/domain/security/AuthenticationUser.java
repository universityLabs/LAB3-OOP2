package com.tech.zootech.security.domain.security;

import com.tech.zootech.security.domain.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class AuthenticationUser extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final Role role;
    private final UUID userId;

    public AuthenticationUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Role role, UUID userId) {
        super(username, password, authorities);
        this.role = role;
        this.userId = userId;
    }

}
