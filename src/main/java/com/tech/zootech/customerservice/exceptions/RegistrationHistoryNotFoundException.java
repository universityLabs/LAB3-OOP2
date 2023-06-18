package com.tech.zootech.customerservice.exceptions;

public class RegistrationHistoryNotFoundException extends RuntimeException {
    public RegistrationHistoryNotFoundException(String message) {
        super(message);
    }
}
