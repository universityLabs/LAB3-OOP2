package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.CourseDto;
import com.tech.zootech.security.domain.Course;
import com.tech.zootech.security.exceptions.CourseIdNotFound;
import com.tech.zootech.security.exceptions.CourseNameNotFound;
import com.tech.zootech.security.exceptions.CourseTeacherNameNotFound;
import com.tech.zootech.security.repo.CourseRepo;
import com.tech.zootech.security.service.CourseValidator;
import com.tech.zootech.security.utils.mapper.CourseMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CourseServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceImplTest {
    @MockBean
    private CourseMapper courseMapper;

    @MockBean
    private CourseRepo courseRepo;

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @MockBean
    private CourseValidator courseValidator;

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDto)}
     */
    @Test
    public void testSave() {
        when(courseMapper.toEntity((CourseDto) any())).thenReturn(new Course());
        when(courseRepo.save((Course) any())).thenReturn(new Course());
        when(courseValidator.valid((CourseDto) any())).thenReturn(true);

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertSame(courseDto, courseServiceImpl.save(courseDto));
        verify(courseMapper).toEntity((CourseDto) any());
        verify(courseRepo).save((Course) any());
        verify(courseValidator).valid((CourseDto) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDto)}
     */
    @Test
    public void testSave2() {
        when(courseMapper.toEntity((CourseDto) any())).thenReturn(new Course());
        when(courseRepo.save((Course) any())).thenThrow(new CourseIdNotFound("Not all who wander are lost"));
        when(courseValidator.valid((CourseDto) any())).thenReturn(true);

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertThrows(CourseIdNotFound.class, () -> courseServiceImpl.save(courseDto));
        verify(courseMapper).toEntity((CourseDto) any());
        verify(courseRepo).save((Course) any());
        verify(courseValidator).valid((CourseDto) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#save(CourseDto)}
     */
    @Test
    public void testSave3() {
        when(courseMapper.toEntity((CourseDto) any())).thenReturn(new Course());
        when(courseRepo.save((Course) any())).thenReturn(new Course());
        when(courseValidator.valid((CourseDto) any())).thenReturn(false);

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertNull(courseServiceImpl.save(courseDto));
        verify(courseValidator).valid((CourseDto) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourseById(Long)}
     */
    @Test
    public void testGetCourseById() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(new Course()));
        assertSame(courseDto, courseServiceImpl.getCourseById(123L));
        verify(courseMapper).toDto((Course) any());
        verify(courseRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourseById(Long)}
     */
    @Test
    public void testGetCourseById2() {
        when(courseMapper.toDto((Course) any())).thenThrow(new CourseIdNotFound("Not all who wander are lost"));
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(new Course()));
        assertThrows(CourseIdNotFound.class, () -> courseServiceImpl.getCourseById(123L));
        verify(courseMapper).toDto((Course) any());
        verify(courseRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourseById(Long)}
     */
    @Test
    public void testGetCourseById3() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        when(courseRepo.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(CourseIdNotFound.class, () -> courseServiceImpl.getCourseById(123L));
        verify(courseRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getByCourseName(String)}
     */
    @Test
    public void testGetByCourseName() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        when(courseRepo.findByCourseName((String) any())).thenReturn(Optional.of(new Course()));
        assertSame(courseDto, courseServiceImpl.getByCourseName("Course Name"));
        verify(courseMapper).toDto((Course) any());
        verify(courseRepo).findByCourseName((String) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getByCourseName(String)}
     */
    @Test
    public void testGetByCourseName2() {
        when(courseMapper.toDto((Course) any())).thenThrow(new CourseIdNotFound("Not all who wander are lost"));
        when(courseRepo.findByCourseName((String) any())).thenReturn(Optional.of(new Course()));
        assertThrows(CourseIdNotFound.class, () -> courseServiceImpl.getByCourseName("Course Name"));
        verify(courseMapper).toDto((Course) any());
        verify(courseRepo).findByCourseName((String) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getByCourseName(String)}
     */
    @Test
    public void testGetByCourseName3() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseName("Course Name");
        courseDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        courseDto.setId(123L);
        courseDto.setStudents(new ArrayList<>());
        courseDto.setTeacherName("Teacher Name");
        courseDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        when(courseRepo.findByCourseName((String) any())).thenReturn(Optional.empty());
        assertThrows(CourseNameNotFound.class, () -> courseServiceImpl.getByCourseName("Course Name"));
        verify(courseRepo).findByCourseName((String) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#getByTeacherName(String)}
     */
    @Test
    public void testGetByTeacherName() {
        when(courseRepo.findByTeacherName((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(courseServiceImpl.getByTeacherName("Teacher Name").isEmpty());
        verify(courseRepo).findByTeacherName((String) any());
    }


    /**
     * Method under test: {@link CourseServiceImpl#getByTeacherName(String)}
     */
    @Test
    public void testGetByTeacherName3() {
        when(courseMapper.toDto((Course) any())).thenThrow(new CourseIdNotFound("Not all who wander are lost"));
        when(courseRepo.findByTeacherName((String) any())).thenReturn(Optional.empty());
        assertThrows(CourseTeacherNameNotFound.class, () -> courseServiceImpl.getByTeacherName("Teacher Name"));
        verify(courseRepo).findByTeacherName((String) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#deleteCourse(Long)}
     */
    @Test
    public void testDeleteCourse() {
        doNothing().when(courseRepo).delete((Course) any());
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(new Course()));
        courseServiceImpl.deleteCourse(123L);
        verify(courseRepo).findById((Long) any());
        verify(courseRepo).delete((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#deleteCourse(Long)}
     */
    @Test
    public void testDeleteCourse2() {
        doThrow(new CourseIdNotFound("Not all who wander are lost")).when(courseRepo).delete((Course) any());
        when(courseRepo.findById((Long) any())).thenReturn(Optional.of(new Course()));
        assertThrows(CourseIdNotFound.class, () -> courseServiceImpl.deleteCourse(123L));
        verify(courseRepo).findById((Long) any());
        verify(courseRepo).delete((Course) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#deleteCourse(Long)}
     */
    @Test
    public void testDeleteCourse3() {
        doNothing().when(courseRepo).delete((Course) any());
        when(courseRepo.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(CourseIdNotFound.class, () -> courseServiceImpl.deleteCourse(123L));
        verify(courseRepo).findById((Long) any());
    }
}

