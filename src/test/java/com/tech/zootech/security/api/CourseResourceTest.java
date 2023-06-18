package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.domain.Course;
import com.tech.zootech.security.repo.CourseRepo;
import com.tech.zootech.security.service.CourseValidator;
import com.tech.zootech.security.service.implementations.CourseServiceImpl;
import com.tech.zootech.security.utils.mapper.CourseMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class CourseResourceTest {

    /**
     * Method under test: {@link CourseResource#saveCourse(CourseDto)}
     */
    @Test
    public void testSaveCourse2() {
        CourseMapper courseMapper = mock(CourseMapper.class);
        when(courseMapper.toEntity((CourseDto) any())).thenReturn(new Course());
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.save((Course) any())).thenReturn(new Course());
        CourseValidator courseValidator = mock(CourseValidator.class);
        when(courseValidator.valid((CourseDto) any())).thenReturn(true);
        CourseResource courseResource = new CourseResource(
                new CourseServiceImpl(courseMapper, courseRepo, courseValidator));

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ResponseEntity<CourseDto> actualSaveCourseResult = courseResource.saveCourse(courseDto);
        assertTrue(actualSaveCourseResult.hasBody());
        assertTrue(actualSaveCourseResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveCourseResult.getStatusCodeValue());
        verify(courseMapper).toEntity((CourseDto) any());
        verify(courseRepo).save((Course) any());
        verify(courseValidator).valid((CourseDto) any());
    }

    /**
     * Method under test: {@link CourseResource#saveCourse(CourseDto)}
     */
    @Test
    public void testSaveCourse3() {
        CourseMapper courseMapper = mock(CourseMapper.class);
        when(courseMapper.toEntity((CourseDto) any())).thenReturn(new Course());
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.save((Course) any())).thenReturn(new Course());
        CourseValidator courseValidator = mock(CourseValidator.class);
        when(courseValidator.valid((CourseDto) any())).thenReturn(false);
        CourseResource courseResource = new CourseResource(
                new CourseServiceImpl(courseMapper, courseRepo, courseValidator));

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ResponseEntity<CourseDto> actualSaveCourseResult = courseResource.saveCourse(courseDto);
        assertNull(actualSaveCourseResult.getBody());
        assertEquals(400, actualSaveCourseResult.getStatusCodeValue());
        assertTrue(actualSaveCourseResult.getHeaders().isEmpty());
        verify(courseValidator).valid((CourseDto) any());
    }

    /**
     * Method under test: {@link CourseResource#deleteCourse(Long)}
     */
    @Test
    public void testDeleteCourse() {
        CourseRepo courseRepo = mock(CourseRepo.class);
        doNothing().when(courseRepo).delete((Course) any());
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(new Course()));
        ResponseEntity<Void> actualDeleteCourseResult = (new CourseResource(
                new CourseServiceImpl(new CourseMapper(), courseRepo, mock(CourseValidator.class)))).deleteCourse(123L);
        assertNull(actualDeleteCourseResult.getBody());
        assertEquals(200, actualDeleteCourseResult.getStatusCodeValue());
        assertTrue(actualDeleteCourseResult.getHeaders().isEmpty());
        verify(courseRepo).findById((Long) any());
        verify(courseRepo).delete((Course) any());
    }

    /**
     * Method under test: {@link CourseResource#getCourseByName(String)}
     */
    @Test
    public void testGetCourseByName2() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        CourseMapper courseMapper = mock(CourseMapper.class);
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.findByCourseName((String) any())).thenReturn(Optional.of(new Course()));
        ResponseEntity<CourseDto> actualCourseByName = (new CourseResource(
                new CourseServiceImpl(courseMapper, courseRepo, mock(CourseValidator.class)))).getCourseByName("Course Name");
        assertTrue(actualCourseByName.hasBody());
        assertTrue(actualCourseByName.getHeaders().isEmpty());
        assertEquals(200, actualCourseByName.getStatusCodeValue());
        verify(courseMapper).toDto((Course) any());
        verify(courseRepo).findByCourseName((String) any());
    }

    /**
     * Method under test: {@link CourseResource#getCourseById(Long)}
     */
    @Test
    public void testGetCourseById2() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        CourseMapper courseMapper = mock(CourseMapper.class);
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(new Course()));
        ResponseEntity<CourseDto> actualCourseById = (new CourseResource(
                new CourseServiceImpl(courseMapper, courseRepo, mock(CourseValidator.class)))).getCourseById(123L);
        assertTrue(actualCourseById.hasBody());
        assertTrue(actualCourseById.getHeaders().isEmpty());
        assertEquals(200, actualCourseById.getStatusCodeValue());
        verify(courseMapper).toDto((Course) any());
        verify(courseRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseResource#getCourseByTeacherName(String)}
     */
    @Test
    public void testGetCourseByTeacherName() {
        CourseRepo courseRepo = mock(CourseRepo.class);
        when(courseRepo.findByTeacherName((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<CourseDto>> actualCourseByTeacherName = (new CourseResource(
                new CourseServiceImpl(new CourseMapper(), courseRepo, mock(CourseValidator.class))))
                .getCourseByTeacherName("Teacher Name");
        assertTrue(actualCourseByTeacherName.hasBody());
        assertEquals(200, actualCourseByTeacherName.getStatusCodeValue());
        assertTrue(actualCourseByTeacherName.getHeaders().isEmpty());
        verify(courseRepo).findByTeacherName((String) any());
    }
}

