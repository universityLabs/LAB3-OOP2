package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper extends AbstractMapper<Course, CourseDto> {
    @Autowired
    public CourseMapper() {
        super(Course.class, CourseDto.class);
    }
}
