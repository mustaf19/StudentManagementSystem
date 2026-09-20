package com.controller;

import java.util.List;
import com.services.StudentService;
import com.services.StudentFileService;
import com.services.JdbcStudentRepository;
import com.objects.Student;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;
import com.exceptions.ValidationException;
import com.exceptions.StudentNotFoundException;
import com.exceptions.RepositoryException;
import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudentsList();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id){
        return studentService.searchStudentById(id);
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student student){
        studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String id, @Valid @RequestBody Student student){
        studentService.updateStudent(id, StudentService.UpdateField.NAME, student.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
    }
}
