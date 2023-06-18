package com.tech.zootech.security.exceptions;

public class ContinentNotFoundException extends RuntimeException {
    public ContinentNotFoundException(String message) {
        super(message);
    }

    public ContinentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
