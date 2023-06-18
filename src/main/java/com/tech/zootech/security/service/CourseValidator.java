package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.CourseDto;

public interface CourseValidator {
    boolean valid(CourseDto courseDto);
}
