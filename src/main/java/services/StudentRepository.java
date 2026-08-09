package services;

import java.util.*;
import objects.Student;

public interface StudentRepository{
    boolean saveData(List<Student> studentList);
    List<Student> loadData();
}