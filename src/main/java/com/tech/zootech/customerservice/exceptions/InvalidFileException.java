package com.tech.zootech.customerservice.exceptions;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String noFileName) {
        super(noFileName);
    }
}
