package com.tech.zootech.security.exceptions;

public class CourseTeacherNameNotFound extends RuntimeException{
    public CourseTeacherNameNotFound(String message) {
        super(message);
    }

    public CourseTeacherNameNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
