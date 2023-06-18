package com.tech.zootech.security.exceptions;

public class CupcakeDroidNotFound extends RuntimeException {
    public CupcakeDroidNotFound(String message) {
        super(message);
    }

    public CupcakeDroidNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
