package services;

import java.util.*;
import objects.Student;
import java.sql.Connection;
import java.sql.DriverManager;


public class JdbcStudentRepository implements StudentRepository {

    private final Connection connection;

    void save(Student student);

    Student findById(String id);

    void deleteById(String id);

    void update(Student student);

    List<Student> findAll();
}