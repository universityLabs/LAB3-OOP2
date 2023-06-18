package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/courses/v1")
@RequiredArgsConstructor
public class CourseResource {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseDto courseDto) {
        var savedCourse = courseService.save(courseDto);
        if (savedCourse == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(savedCourse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCourse(@RequestParam("id") Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("course/{courseName}")
    public ResponseEntity<CourseDto> getCourseByName(@PathVariable("courseName") String courseName) {
        return ResponseEntity.ok().body(courseService.getByCourseName(courseName));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(courseService.getCourseById(id));
    }

    @GetMapping("teacher/{teacherName}")
    public ResponseEntity<List<CourseDto>> getCourseByTeacherName(@PathVariable("teacherName") String teacherName) {
        return ResponseEntity.ok().body(courseService.getByTeacherName(teacherName));
    }
}
