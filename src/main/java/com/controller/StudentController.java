package com.controller;

import java.util.List;
import com.services.StudentService;
import com.dto.CreateStudentRequest;
import com.dto.StudentMapper;
import com.dto.StudentPage;
import com.dto.StudentResponse;
import com.dto.UpdateStudentRequest;
import com.objects.Student;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // @GetMapping
    // public List<Student> getStudents(){
    //     return studentService.getStudentsList();
    // }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable String id){
        return StudentMapper.toResponse(studentService.searchStudentById(id));
    }

    @GetMapping
    public StudentPage getStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return studentService.getStudents(page, size);
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody CreateStudentRequest req){
        studentService.addStudent(StudentMapper.toStudent(req));
    }

    // @PutMapping("/{id}")
    // public void updateStudent(@PathVariable String id, @Valid @RequestBody Student student){
    //     studentService.updateStudent(id, StudentService.UpdateField.NAME, student.getName());
    // }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String id, @RequestBody @Valid UpdateStudentRequest req){
        studentService.updateStudent(id, StudentMapper.toStudent(req));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id){
        studentService.deleteStudent(id);
    }
}
