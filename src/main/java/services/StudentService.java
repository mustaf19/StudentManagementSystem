package services;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import objects.Student;
import java.util.Collections;
// import services.StudentFileService; // same package doesnt require importing

public class StudentService{

    private List<Student> studentList;
    private final StudentFileService sfs;
    private Set<String> studentIds = new HashSet<String>();

    private void persistData(){
        this.sfs.saveData(this.studentList);
    }

    public StudentService(StudentFileService sfs){
        this.sfs = sfs;
        this.studentList = sfs.loadData();
        for(Student student: studentList){
            studentIds.add(student.getId());
        }
    }

    public boolean checkEmail(Student student){

        if(student.getEmail()!= null && student.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") == false){
            System.out.println("Something in Wmail is wrong");
             System.out.println(student.getEmail());
             return false;
        }
        return true;

    }

    public boolean checkPhone(Student student){
        if(student.getPhoneNo()!= null && student.getPhoneNo().matches("\\d{10}$") == false){
            System.out.println("Something in PhoneNo is wrong");
            System.out.println(student.getPhoneNo());
            return false;
        }
        return true;
        // return student.getPhoneNo()!= null && student.getPhoneNo().matches("\\d{10}");

    }

    public boolean isUniqueId(Student student){
        if(studentIds.contains(student.getId())){
            System.out.println("Id already exists");
            return false;
        }
        return true;
    }

    public boolean addStudent(Student student){
        if(this.checkEmail(student)==false) return false;
        if(this.checkPhone(student)==false) return false;
        if(this.isUniqueId(student)==false) return false;
        if(this.searchStudentById(student.getId())==null){
            this.studentList.add(student);
            this.persistData();
            return true;
        }
        return false;
    }

    public List<Student> getStudentsList(){
        // return this.studentList;
        return Collections.unmodifiableList(this.studentList);
    }

    public Student searchStudentById(String id){
        for(Student student: this.studentList){
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
        this.persistData();
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
        this.persistData();
        return true;
    }

}