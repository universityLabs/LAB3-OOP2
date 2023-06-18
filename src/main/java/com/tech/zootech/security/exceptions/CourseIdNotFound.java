package com.tech.zootech.security.exceptions;

public class CourseIdNotFound extends RuntimeException {
    public CourseIdNotFound(String message) {
        super(message);
    }

    public CourseIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
