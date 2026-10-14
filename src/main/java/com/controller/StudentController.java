package com.controller;

import com.services.StudentService;
import com.dto.CreateStudentRequest;
import com.dto.StudentMapper;
import com.dto.StudentPage;
import com.dto.StudentResponse;
import com.dto.UpdateStudentRequest;
import com.objects.Student;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Tag(name = "Students", description = "Create, read, update, and delete students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // @GetMapping
    // public List<Student> getStudents(){
    //     return studentService.getStudentsList();
    // }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public StudentResponse getStudent(
            @Parameter(description = "Student ID") @PathVariable String id) {
        return StudentMapper.toResponse(studentService.searchStudentById(id));
    }

    @Operation(summary = "List students (paged)")
    @GetMapping
    public StudentPage getStudents(
            @Parameter(description = "Zero-based page index") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size (max 100)") @RequestParam(defaultValue = "10") int size) {
        return studentService.getStudents(page, size);
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody CreateStudentRequest req) {
        Student created = studentService.addStudent(StudentMapper.toStudent(req));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StudentMapper.toResponse(created));
    }

    // @PutMapping("/{id}")
    // public void updateStudent(@PathVariable String id, @Valid @RequestBody Student student){
    //     studentService.updateStudent(id, StudentService.UpdateField.NAME, student.getName());
    // }

    @Operation(summary = "Update an existing student")
    @PutMapping("/{id}")
    public void updateStudent(
            @Parameter(description = "Student ID") @PathVariable String id,
            @RequestBody @Valid UpdateStudentRequest req) {
        studentService.updateStudent(id, StudentMapper.toStudent(req));
    }

    @Operation(summary = "Delete a student by ID")
    @DeleteMapping("/{id}")
    public void deleteStudent(
            @Parameter(description = "Student ID") @PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
