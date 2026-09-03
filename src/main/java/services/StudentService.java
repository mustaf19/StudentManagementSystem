package services;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import objects.Student;
import java.util.Collections;
import exceptions.ValidationException;
import exceptions.StudentNotFoundException;
import exceptions.RepositoryException;

public class StudentService{

    private final StudentRepository sri;
    private Set<String> studentIds = new HashSet<String>();

    public StudentService(StudentRepository sri){
        this.sri = sri;
    }

    public void checkEmail(String email){
        if(email!= null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") == false){
            throw new ValidationException("Invalid Email");
        }
    }

    public void checkPhone(String phoneNo){
        if(phoneNo!= null && phoneNo.matches("\\d{10}$") == false){
            throw new ValidationException("Invalid Phoneno");
        }
    }

    public void isUniqueId(String id){
        if(studentIds.contains(id)){
            throw new ValidationException("Not unique id");
        }
    }

    public void addStudent(Student student){
        this.checkEmail(student.getEmail());
        this.checkPhone(student.getPhoneNo());
        this.isUniqueId(student.getId());
        if(this.searchStudentById(student.getId())==null){
            try{
                this.sri.save(student);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            throw new ValidationException("Student is available");
        }
    }

    public List<Student> getStudentsList(){
        return Collections.unmodifiableList(this.sri.findAll());
    }

    public Student searchStudentById(String id){
        Student returnStudent = this.sri.findById(id);
        if(returnStudent==null){
            throw new StudentNotFoundException("Student with id "+id+" not found!");
        }
        else{
            return returnStudent;
        }
    }


    public void deleteStudent(String id){
        Student studentTobeDeleted = this.searchStudentById(id);

        if(studentTobeDeleted == null){
            throw new StudentNotFoundException("Student with id "+id+ " not found.");
        }
        try{
            this.sri.deleteById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateStudent(String id, String paramater, String updatedValue){
        Student studentToBeUpdated = this.searchStudentById(id);
        if(studentToBeUpdated==null || updatedValue==null || paramater ==null){
            throw new StudentNotFoundException("Student not found!");
        }

        switch(paramater){
            case "NAME": studentToBeUpdated.setName(updatedValue); break;
            case "ADDRESS": 
                studentToBeUpdated.setAddress(updatedValue); break;
            case "PHONENO": 
                this.checkPhone(updatedValue);
                studentToBeUpdated.setPhoneNo(updatedValue);
                break;
            case "EMAIL": 
                this.checkEmail(updatedValue);
                studentToBeUpdated.setEmail(updatedValue); 
                break;
            case "DOB": studentToBeUpdated.setDob(updatedValue); break;
            default: throw new ValidationException("Unknown field: " + paramater);
        }
        this.sri.update(studentToBeUpdated);
    }
}