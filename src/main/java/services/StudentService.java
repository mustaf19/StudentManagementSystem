package services;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import objects.Student;
import java.util.Collections;
// import services.StudentRepository; // same package doesnt require importing

public class StudentService{

    private List<Student> studentList;
    private final StudentRepository sri;
    private Set<String> studentIds = new HashSet<String>();

    private void persistData(){
        this.sri.saveData(this.studentList);
    }

    public StudentService(StudentRepository sri){
        this.sri = sri;
        this.studentList = sri.loadData();
        for(Student student: studentList){
            studentIds.add(student.getId());
        }
    }

    public boolean checkEmail(String email){

        if(email!= null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") == false){
            System.out.println("Something in Wmail is wrong");
             System.out.println(email);
             return false;
        }
        return true;
    }

    public boolean checkPhone(String phoneNo){
        if(phoneNo!= null && phoneNo.matches("\\d{10}$") == false){
            System.out.println("Something in PhoneNo is wrong");
            System.out.println(phoneNo);
            return false;
        }
        return true;
        // return student.getPhoneNo()!= null && student.getPhoneNo().matches("\\d{10}");

    }

    public boolean isUniqueId(String id){
        if(studentIds.contains(id)){
            System.out.println("Id already exists");
            return false;
        }
        return true;
    }

    public boolean addStudent(Student student){
        if(this.checkEmail(student.getEmail())==false) return false;
        if(this.checkPhone(student.getPhoneNo())==false) return false;
        if(this.isUniqueId(student.getId())==false) return false;
        if(this.searchStudentById(student.getId())==null){
            this.studentList.add(student);
            this.studentIds.add(student.getId());
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
        this.studentIds.remove(studentTobeDeleted.getId());
        this.persistData();
        return true;
    }

    public boolean updateStudent(String id, String paramater, String updatedValue){
        Student studentToBeUpdated = this.searchStudentById(id);
        if(studentToBeUpdated==null){
            return false;
        }
        // if(paramater.equals("Name")){
        //     studentToBeUpdated.setName(updatedValue);
        // }
        switch(paramater){
            case "NAME": studentToBeUpdated.setName(updatedValue); break;
            case "ADDRESS": studentToBeUpdated.setAddress(updatedValue); break;
            case "PHONENO": studentToBeUpdated.setPhoneNo(updatedValue); break;
            case "EMAIL": studentToBeUpdated.setEmail(updatedValue); break;
            case "DOB": studentToBeUpdated.setDob(updatedValue); break;
            default: break;
        }
        this.persistData();
        return true;
    }

}