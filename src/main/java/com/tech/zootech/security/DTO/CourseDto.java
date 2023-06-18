package com.tech.zootech.security.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class CourseDto extends AbstractDto {
    private String courseName;
    private String teacherName;
    private Collection<StudentDto> students;
}
