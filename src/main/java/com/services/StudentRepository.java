package com.services;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.objects.Student;

@Repository 
public interface StudentRepository {

    void save(Student student);

    Optional<Student> findById(String id);

    void deleteById(String id);

    void update(Student student);

    List<Student> findAll();

    List<Student> findPage(int offset, int limit);

    long count();

}