package com.tech.zootech.security.service;

import com.tech.zootech.security.DTO.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto save(StudentDto dto);
    StudentDto get(Long id);
    StudentDto getByNameAndSurname(String name, String surname);
    List<StudentDto> getByStudentSurname(String surname);
}
