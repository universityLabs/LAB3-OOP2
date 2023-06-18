package com.tech.zootech.security.exceptions;

public class HeroNotFound extends RuntimeException {
    public HeroNotFound(String message) {
        super(message);
    }

    public HeroNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
