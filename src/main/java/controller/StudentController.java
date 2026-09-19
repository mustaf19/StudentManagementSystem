import java.util.List;
import services.StudentService;
import services.StudentFileService;
import services.JdbcStudentRepository;
import objects.Student;
import java.util.Scanner;
import java.util.UUID;
import java.time.LocalDate;
import exceptions.ValidationException;
import exceptions.StudentNotFoundException;
import exceptions.RepositoryException;
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
