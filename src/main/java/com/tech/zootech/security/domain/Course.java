package com.tech.zootech.security.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "courses")
public class Course extends AbstractEntity {
    @Column(name = "name")
    private String courseName;

    @Column(name = "teacher_name")
    private String teacherName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Collection<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (getCourseName() != null ? !getCourseName().equals(course.getCourseName()) : course.getCourseName() != null)
            return false;
        if (getTeacherName() != null ? !getTeacherName().equals(course.getTeacherName()) : course.getTeacherName() != null)
            return false;
        return getStudents() != null ? getStudents().equals(course.getStudents()) : course.getStudents() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCourseName() != null ? getCourseName().hashCode() : 0);
        result = 31 * result + (getTeacherName() != null ? getTeacherName().hashCode() : 0);
        result = 31 * result + (getStudents() != null ? getStudents().hashCode() : 0);
        return result;
    }
}
