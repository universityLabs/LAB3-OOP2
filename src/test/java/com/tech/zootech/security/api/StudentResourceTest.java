package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.StudentDto;
import com.tech.zootech.security.domain.Student;
import com.tech.zootech.security.repo.StudentRepo;
import com.tech.zootech.security.service.implementations.StudentServiceImpl;
import com.tech.zootech.security.utils.mapper.StudentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class StudentResourceTest {
    /**
     * Method under test: {@link StudentResource#get(Long)}
     */
    @Test
    public void testGet2() {
        StudentRepo studentRepo = mock(StudentRepo.class);
        when(studentRepo.findById((Long) any())).thenReturn(Optional.of(new Student()));

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        StudentMapper studentMapper = mock(StudentMapper.class);
        when(studentMapper.toDto((Student) any())).thenReturn(studentDto);
        ResponseEntity<StudentDto> actualGetResult = (new StudentResource(
                new StudentServiceImpl(studentRepo, studentMapper))).get(123L);
        assertTrue(actualGetResult.hasBody());
        assertTrue(actualGetResult.getHeaders().isEmpty());
        assertEquals(200, actualGetResult.getStatusCodeValue());
        verify(studentRepo).findById((Long) any());
        verify(studentMapper).toDto((Student) any());
    }

    /**
     * Method under test: {@link StudentResource#save(StudentDto)}
     */
    @Test
    public void testSave2() {
        StudentRepo studentRepo = mock(StudentRepo.class);
        when(studentRepo.save((Student) any())).thenReturn(new Student());

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        StudentMapper studentMapper = mock(StudentMapper.class);
        when(studentMapper.toDto((Student) any())).thenReturn(studentDto);
        when(studentMapper.toEntity((StudentDto) any())).thenReturn(new Student());
        StudentResource studentResource = new StudentResource(new StudentServiceImpl(studentRepo, studentMapper));

        StudentDto studentDto1 = new StudentDto();
        studentDto1.setCourses(new ArrayList<>());
        studentDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto1.setId(123L);
        studentDto1.setStudentName("Student Name");
        studentDto1.setStudentSurname("Doe");
        studentDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        ResponseEntity<StudentDto> actualSaveResult = studentResource.save(studentDto1);
        assertEquals(studentDto1, actualSaveResult.getBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(studentRepo).save((Student) any());
        verify(studentMapper).toDto((Student) any());
        verify(studentMapper).toEntity((StudentDto) any());
    }


    /**
     * Method under test: {@link StudentResource#getStudentByNameAndSurname(String, String)}
     */
    @Test
    public void testGetStudentByNameAndSurname2() {
        StudentRepo studentRepo = mock(StudentRepo.class);
        when(studentRepo.findStudentByStudentNameAndStudentSurname((String) any(), (String) any())).thenReturn(null);
        ResponseEntity<StudentDto> actualStudentByNameAndSurname = (new StudentResource(
                new StudentServiceImpl(studentRepo, new StudentMapper()))).getStudentByNameAndSurname("Name", "Doe");
        assertNull(actualStudentByNameAndSurname.getBody());
        assertEquals(200, actualStudentByNameAndSurname.getStatusCodeValue());
        assertTrue(actualStudentByNameAndSurname.getHeaders().isEmpty());
        verify(studentRepo).findStudentByStudentNameAndStudentSurname((String) any(), (String) any());
    }

    /**
     * Method under test: {@link StudentResource#getStudentListBySurname(String)}
     */
    @Test
    public void testGetStudentListBySurname() {
        StudentRepo studentRepo = mock(StudentRepo.class);
        when(studentRepo.findByStudentSurname((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<StudentDto>> actualStudentListBySurname = (new StudentResource(
                new StudentServiceImpl(studentRepo, new StudentMapper()))).getStudentListBySurname("Doe");
        assertTrue(actualStudentListBySurname.hasBody());
        assertEquals(200, actualStudentListBySurname.getStatusCodeValue());
        assertTrue(actualStudentListBySurname.getHeaders().isEmpty());
        verify(studentRepo).findByStudentSurname((String) any());
    }
}

