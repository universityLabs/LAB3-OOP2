package com.tech.zootech.customerservice.exceptions;

public class ImpossibleFileExtractionException extends RuntimeException {
    public ImpossibleFileExtractionException(String message) {
        super(message);
    }

    public ImpossibleFileExtractionException(String message, Throwable cause) {
        super(message, cause);
    }
}
