package com.tech.zootech.security.exceptions;

public class UnicornNameNotFound extends RuntimeException {
    public UnicornNameNotFound(String message) {
        super(message);
    }

    public UnicornNameNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
