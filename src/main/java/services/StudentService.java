package services;

import java.util.List;
import java.util.ArrayList;
import objects.Student;

public class StudentService{

private List<Student> studentList = new ArrayList<>();

    public boolean addStudent(Student student){
        if(this.searchStudentById(student.getId())==null){
            this.studentList.add(student);
            return true;
        }
        return false;
    }

    public List<Student> getStudentsList(){
        return this.studentList;
    }

    public Student searchStudentById(String id){
        for(Student student: studentList){
            if(student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }


    public boolean deleteStudent(String id){
        Student studentTobeDeleted = this.searchStudentById(id);
        if(studentTobeDeleted == null){
            return false;
        }
        this.studentList.remove(studentTobeDeleted);
        return true;
    }

    public boolean updateStudent(String id, String paramater, String updatedValue){
        Student studentToBeUpdated = this.searchStudentById(id);
        if(studentToBeUpdated==null){
            return false;
        }
        if(paramater.equals("Name")){
            studentToBeUpdated.setName(updatedValue);
        }
        return true;

    }

}