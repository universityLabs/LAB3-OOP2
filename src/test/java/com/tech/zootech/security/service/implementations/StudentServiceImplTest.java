package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.StudentDto;
import com.tech.zootech.security.domain.Student;
import com.tech.zootech.security.exceptions.StudentSurnameNotFound;
import com.tech.zootech.security.repo.StudentRepo;
import com.tech.zootech.security.utils.mapper.StudentMapper;

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

@ContextConfiguration(classes = {StudentServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceImplTest {
    @MockBean
    private StudentMapper studentMapper;

    @MockBean
    private StudentRepo studentRepo;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /**
     * Method under test: {@link StudentServiceImpl#save(StudentDto)}
     */
    @Test
    public void testSave() {
        when(studentRepo.save((Student) any())).thenReturn(new Student());

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentMapper.toDto((Student) any())).thenReturn(studentDto);
        when(studentMapper.toEntity((StudentDto) any())).thenReturn(new Student());

        StudentDto studentDto1 = new StudentDto();
        studentDto1.setCourses(new ArrayList<>());
        studentDto1.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto1.setId(123L);
        studentDto1.setStudentName("Student Name");
        studentDto1.setStudentSurname("Doe");
        studentDto1.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertSame(studentDto, studentServiceImpl.save(studentDto1));
        verify(studentRepo).save((Student) any());
        verify(studentMapper).toDto((Student) any());
        verify(studentMapper).toEntity((StudentDto) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#save(StudentDto)}
     */
    @Test
    public void testSave2() {
        when(studentRepo.save((Student) any())).thenReturn(new Student());
        when(studentMapper.toDto((Student) any())).thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));
        when(studentMapper.toEntity((StudentDto) any()))
                .thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        assertThrows(StudentSurnameNotFound.class, () -> studentServiceImpl.save(studentDto));
        verify(studentMapper).toEntity((StudentDto) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#get(Long)}
     */
    @Test
    public void testGet() {
        when(studentRepo.findById((Long) any())).thenReturn(Optional.of(new Student()));

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentMapper.toDto((Student) any())).thenReturn(studentDto);
        assertSame(studentDto, studentServiceImpl.get(123L));
        verify(studentRepo).findById((Long) any());
        verify(studentMapper).toDto((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#get(Long)}
     */
    @Test
    public void testGet2() {
        when(studentRepo.findById((Long) any())).thenReturn(Optional.of(new Student()));
        when(studentMapper.toDto((Student) any())).thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));
        assertThrows(StudentSurnameNotFound.class, () -> studentServiceImpl.get(123L));
        verify(studentRepo).findById((Long) any());
        verify(studentMapper).toDto((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#get(Long)}
     */
    @Test
    public void testGet3() {
        Student student = mock(Student.class);
        when(student.getStudentName()).thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));
        when(student.getStudentSurname()).thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepo.findById((Long) any())).thenReturn(ofResult);

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentMapper.toDto((Student) any())).thenReturn(studentDto);
        assertThrows(StudentSurnameNotFound.class, () -> studentServiceImpl.get(123L));
        verify(studentRepo).findById((Long) any());
        verify(student).getStudentName();
    }

    /**
     * Method under test: {@link StudentServiceImpl#getByNameAndSurname(String, String)}
     */
    @Test
    public void testGetByNameAndSurname() {
        when(studentRepo.findStudentByStudentNameAndStudentSurname((String) any(), (String) any()))
                .thenReturn(new Student());

        StudentDto studentDto = new StudentDto();
        studentDto.setCourses(new ArrayList<>());
        studentDto.setCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        studentDto.setId(123L);
        studentDto.setStudentName("Student Name");
        studentDto.setStudentSurname("Doe");
        studentDto.setUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(studentMapper.toDto((Student) any())).thenReturn(studentDto);
        assertSame(studentDto, studentServiceImpl.getByNameAndSurname("Name", "Doe"));
        verify(studentRepo).findStudentByStudentNameAndStudentSurname((String) any(), (String) any());
        verify(studentMapper).toDto((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#getByNameAndSurname(String, String)}
     */
    @Test
    public void testGetByNameAndSurname2() {
        when(studentRepo.findStudentByStudentNameAndStudentSurname((String) any(), (String) any()))
                .thenReturn(new Student());
        when(studentMapper.toDto((Student) any())).thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));
        assertThrows(StudentSurnameNotFound.class, () -> studentServiceImpl.getByNameAndSurname("Name", "Doe"));
        verify(studentRepo).findStudentByStudentNameAndStudentSurname((String) any(), (String) any());
        verify(studentMapper).toDto((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#getByStudentSurname(String)}
     */
    @Test
    public void testGetByStudentSurname() {
        when(studentRepo.findByStudentSurname((String) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(studentServiceImpl.getByStudentSurname("Doe").isEmpty());
        verify(studentRepo).findByStudentSurname((String) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#getByStudentSurname(String)}
     */
    @Test
    public void testGetByStudentSurname3() {
        when(studentRepo.findByStudentSurname((String) any())).thenReturn(Optional.empty());
        when(studentMapper.toDto((Student) any())).thenThrow(new StudentSurnameNotFound("Not all who wander are lost"));
        assertThrows(StudentSurnameNotFound.class, () -> studentServiceImpl.getByStudentSurname("Doe"));
        verify(studentRepo).findByStudentSurname((String) any());
    }
}

