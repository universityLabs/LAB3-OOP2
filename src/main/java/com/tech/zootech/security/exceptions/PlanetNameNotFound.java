package com.tech.zootech.security.exceptions;

public class PlanetNameNotFound extends RuntimeException {
    public PlanetNameNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public PlanetNameNotFound(String message) {
        super(message);
    }
}
