package com.tech.zootech.security.exceptions;

public class DroidNotFound extends RuntimeException {
    public DroidNotFound(String message) {
        super(message);
    }

    public DroidNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
