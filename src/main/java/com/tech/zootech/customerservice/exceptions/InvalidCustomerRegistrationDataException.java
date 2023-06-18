package com.tech.zootech.customerservice.exceptions;

public class InvalidCustomerRegistrationDataException extends RuntimeException {
    public InvalidCustomerRegistrationDataException(String message) {
        super(message);
    }
}
