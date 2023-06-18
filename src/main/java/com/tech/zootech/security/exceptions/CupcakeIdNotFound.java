package com.tech.zootech.security.exceptions;

public class CupcakeIdNotFound extends RuntimeException {
    public CupcakeIdNotFound(String message) {
        super(message);
    }

    public CupcakeIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
