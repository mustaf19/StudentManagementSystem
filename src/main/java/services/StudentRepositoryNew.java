package services;

import java.util.*;
import objects.Student;


public interface StudentRepositoryNew {

    void save(Student student);

    Student findById(String id);

    void deleteById(String id);

    void update(Student student);

    List<Student> findAll();
}