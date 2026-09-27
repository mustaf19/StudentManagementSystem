package com.services;

import java.util.*;
import com.objects.Student;

// @Repository
public interface StudentRepository {

    void save(Student student);

    Optional<Student> findById(String id);

    void deleteById(String id);

    void update(Student student);

    List<Student> findAll();
}