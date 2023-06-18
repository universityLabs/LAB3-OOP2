package com.tech.zootech.security.utils.mapper;

import com.tech.zootech.security.DTO.StudentDto;
import com.tech.zootech.security.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper extends AbstractMapper<Student, StudentDto> {
    @Autowired
    public StudentMapper() {
        super(Student.class, StudentDto.class);
    }
}
