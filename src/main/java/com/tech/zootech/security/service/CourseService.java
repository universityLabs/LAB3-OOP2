package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto save(CourseDto dto);
    CourseDto getCourseById(Long id);
    CourseDto getByCourseName(String name);
    List<CourseDto> getByTeacherName(String name);
    void deleteCourse(Long id);
}
