package services;

import java.util.List;
import java.util.ArrayList;
import objects.Student;

public class InMemoryStudentRepository implements StudentRepository {

    private List<Student> students = new ArrayList<>();

    public void save(Student student){
        students.add(student);
    }

    public Student findById(String id){
        for(Student x: students){
            if(x.getId().equals(id)){
                return x;
            }
        }
        return null;
    }

    public void deleteById(String id){
        for(Student x: students){
            if(x.getId().equals(id)){
                students.remove(x);
            }
        }
    }

    public void update(Student student){
        for(Student x: students){
            if(x.getId().equals(student.getId())){
                students.remove(x);
                students.add(student);
            }
        }
    }

    public List<Student> findAll(){
        return students;
    }

    // ==================
    // private List<Student> students = new ArrayList<>();

    // @Override
    // public boolean saveData(List<Student> studentList) {
    //     this.students = studentList;
    //     return true;
    // }

    // @Override
    // public List<Student> loadData() {
    //     return students;
    // }
}