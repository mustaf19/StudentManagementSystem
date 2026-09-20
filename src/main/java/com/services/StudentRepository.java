package com.services;

import java.util.*;
import com.objects.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository
public interface StudentRepository {

    void save(Student student);

    Optional<Student> findById(String id);

    void deleteById(String id);

    void update(Student student);

    List<Student> findAll();
}