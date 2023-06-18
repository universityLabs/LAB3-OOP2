package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.DTO.StudentDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CourseDefaultValidator.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseDefaultValidatorTest {
    @Autowired
    private CourseDefaultValidator courseDefaultValidator;

    /**
     * Method under test: {@link CourseDefaultValidator#valid(CourseDto)}
     */
    @Test
    public void testValid() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(courseDefaultValidator.valid(courseDto));
    }

    /**
     * Method under test: {@link CourseDefaultValidator#valid(CourseDto)}
     */
    @Test
    public void testValid2() {
        CourseDto courseDto = mock(CourseDto.class);
        when(courseDto.getCourseName()).thenReturn("Course Name");
        doNothing().when(courseDto).setCreated((LocalDateTime) any());
        doNothing().when(courseDto).setId((Long) any());
        doNothing().when(courseDto).setUpdated((LocalDateTime) any());
        doNothing().when(courseDto).setCourseName((String) any());
        doNothing().when(courseDto).setStudents((Collection<StudentDto>) any());
        doNothing().when(courseDto).setTeacherName((String) any());
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(courseDefaultValidator.valid(courseDto));
        verify(courseDto).getCourseName();
        verify(courseDto).setCreated((LocalDateTime) any());
        verify(courseDto).setId((Long) any());
        verify(courseDto).setUpdated((LocalDateTime) any());
        verify(courseDto).setCourseName((String) any());
        verify(courseDto).setStudents((Collection<StudentDto>) any());
        verify(courseDto).setTeacherName((String) any());
    }

    /**
     * Method under test: {@link CourseDefaultValidator#valid(CourseDto)}
     */
    @Test
    public void testValid3() {
        CourseDto courseDto = mock(CourseDto.class);
        when(courseDto.getStudents()).thenReturn(new ArrayList<>());
        when(courseDto.getTeacherName()).thenReturn("Teacher Name");
        when(courseDto.getCourseName()).thenReturn("course");
        doNothing().when(courseDto).setCreated((LocalDateTime) any());
        doNothing().when(courseDto).setId((Long) any());
        doNothing().when(courseDto).setUpdated((LocalDateTime) any());
        doNothing().when(courseDto).setCourseName((String) any());
        doNothing().when(courseDto).setStudents((Collection<StudentDto>) any());
        doNothing().when(courseDto).setTeacherName((String) any());
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertFalse(courseDefaultValidator.valid(courseDto));
        verify(courseDto).getCourseName();
        verify(courseDto).getTeacherName();
        verify(courseDto).getStudents();
        verify(courseDto).setCreated((LocalDateTime) any());
        verify(courseDto).setId((Long) any());
        verify(courseDto).setUpdated((LocalDateTime) any());
        verify(courseDto).setCourseName((String) any());
        verify(courseDto).setStudents((Collection<StudentDto>) any());
        verify(courseDto).setTeacherName((String) any());
    }

    /**
     * Method under test: {@link CourseDefaultValidator#valid(CourseDto)}
     */
    @Test
    public void testValid4() {
        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("course");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));

        ArrayList<StudentDto> studentDtoList = new ArrayList<>();
        studentDtoList.add(studentDto);
        CourseDto courseDto = mock(CourseDto.class);
        when(courseDto.getStudents()).thenReturn(studentDtoList);
        when(courseDto.getTeacherName()).thenReturn("Teacher Name");
        when(courseDto.getCourseName()).thenReturn("course");
        doNothing().when(courseDto).setCreated((LocalDateTime) any());
        doNothing().when(courseDto).setId((Long) any());
        doNothing().when(courseDto).setUpdated((LocalDateTime) any());
        doNothing().when(courseDto).setCourseName((String) any());
        doNothing().when(courseDto).setStudents((Collection<StudentDto>) any());
        doNothing().when(courseDto).setTeacherName((String) any());
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertTrue(courseDefaultValidator.valid(courseDto));
        verify(courseDto).getCourseName();
        verify(courseDto).getTeacherName();
        verify(courseDto).getStudents();
        verify(courseDto).setCreated((LocalDateTime) any());
        verify(courseDto).setId((Long) any());
        verify(courseDto).setUpdated((LocalDateTime) any());
        verify(courseDto).setCourseName((String) any());
        verify(courseDto).setStudents((Collection<StudentDto>) any());
        verify(courseDto).setTeacherName((String) any());
    }
}

