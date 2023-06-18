package com.tech.zootech.security.domain.subdomain.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "cognitoUsername")
@Inheritance
public abstract class Sponsor {
    @Column(nullable = false, updatable = false, unique = true)
    private String cognitoUsername;

    @Column(nullable = false, unique = true)
    private String email;
}
