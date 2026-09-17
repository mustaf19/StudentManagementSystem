package services;

import java.util.*;
import objects.Student;


public interface StudentRepository {

    void save(Student student);

    Optional<Student> findById(String id);

    void deleteById(String id);

    void update(Student student);

    List<Student> findAll();
}