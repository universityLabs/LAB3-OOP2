package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findStudentByStudentNameAndStudentSurname(String name, String surname);
    Optional<List<Student>> findByStudentSurname(String surname);
}
