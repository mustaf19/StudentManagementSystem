package services;

import java.util.List;
import java.util.ArrayList;
import objects.Student;

public class InMemoryStudentRepository implements StudentRepository {

    private List<Student> students = new ArrayList<>();

    @Override
    public boolean saveData(List<Student> studentList) {
        this.students = studentList;
        return true;
    }

    @Override
    public List<Student> loadData() {
        return students;
    }
}