package com.tech.zootech.security.api;

import com.tech.zootech.security.DTO.StudentDto;
import com.tech.zootech.security.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students/v1")
@RequiredArgsConstructor
public class StudentResource {
    private final StudentService studentService;

    @GetMapping("/get-student")
    public ResponseEntity<StudentDto> get(@RequestParam Long id) {
        return ResponseEntity.ok().body(studentService.get(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok().body(studentService.save(studentDto));
    }

    @GetMapping("/get-student-by-name-and-surname")
    public ResponseEntity<StudentDto> getStudentByNameAndSurname(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname) {
        return ResponseEntity.ok().body(studentService.getByNameAndSurname(name, surname));
    }

    @GetMapping("/student/{surname}")
    public ResponseEntity<List<StudentDto>> getStudentListBySurname(
            @PathVariable(name = "surname") String surname) {
        return ResponseEntity.ok(studentService.getByStudentSurname(surname));
    }
}
