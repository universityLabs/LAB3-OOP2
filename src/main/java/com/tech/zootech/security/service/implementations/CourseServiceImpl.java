package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.exceptions.CourseIdNotFound;
import com.tech.zootech.security.exceptions.CourseNameNotFound;
import com.tech.zootech.security.exceptions.CourseTeacherNameNotFound;
import com.tech.zootech.security.repo.CourseRepo;
import com.tech.zootech.security.service.CourseService;
import com.tech.zootech.security.service.CourseValidator;
import com.tech.zootech.security.utils.mapper.CourseMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("defaultCourseService")
@Slf4j
@AllArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepo courseRepo;
    private final CourseValidator courseValidator;

    @Override
    public CourseDto save(CourseDto dto) {
        if (!courseValidator.valid(dto)) {
            return null;
        }
        log.info("saving course with course name: " + dto.getCourseName() + " to db");
        var course = courseMapper.toEntity(dto);
        courseRepo.save(course);
        return dto;
    }

    @Override
    public CourseDto getCourseById(Long id) {
        log.info("getting course with id: " + id + " from db");
        var course = courseRepo.findById(id).orElseThrow(() ->
                new CourseIdNotFound("course with id" + id + " not found"));
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto getByCourseName(String courseName) {
        log.info("getting course by course name");
        var course = courseRepo.findByCourseName(courseName).orElseThrow(() ->
                new CourseNameNotFound("course with name "+courseName+ " not found in db"));
        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDto> getByTeacherName(String teacherName) {
        log.info("getting courses by teacher name");
        var courses = courseRepo.findByTeacherName(teacherName).orElseThrow(() ->
                new CourseTeacherNameNotFound("course with teacher name "+teacherName+ " not found in db"));
        return courses
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    public void deleteCourse(Long id) {
        log.info("deleting course by id: " + id);
        var course = courseRepo.findById(id).orElseThrow(() ->
                new CourseIdNotFound("course with id" + id + " not found"));
        courseRepo.delete(course);
    }
}
