package services;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import objects.Student;
import java.util.Collections;
// import java.util.Scanner;
// import services.StudentRepository; // same package doesnt require importing

public class StudentService{

    private final StudentRepository sri;
    private Set<String> studentIds = new HashSet<String>();

    public StudentService(StudentRepository sri){
        this.sri = sri;
    }

    public boolean checkEmail(String email){
        if(email!= null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") == false){
            return false;
        }
        return true;
    }

    public boolean checkPhone(String phoneNo){
        if(phoneNo!= null && phoneNo.matches("\\d{10}$") == false){
            return false;
        }
        return true;
        // return student.getPhoneNo()!= null && student.getPhoneNo().matches("\\d{10}");
    }

    public boolean isUniqueId(String id){
        if(studentIds.contains(id)){
            return false;
        }
        return true;
    }

    public boolean addStudent(Student student){
        if(this.checkEmail(student.getEmail())==false) return false;
        if(this.checkPhone(student.getPhoneNo())==false) return false;
        if(this.isUniqueId(student.getId())==false) return false;
        if(this.searchStudentById(student.getId())==null){
            try{
                this.sri.save(student);
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    public List<Student> getStudentsList(){
        return Collections.unmodifiableList(this.sri.findAll());
    }

    public Student searchStudentById(String id){
        return this.sri.findById(id);
    }


    public boolean deleteStudent(String id){
        Student studentTobeDeleted = this.searchStudentById(id);

        if(studentTobeDeleted == null){
            return false;
        }
        try{
            this.sri.deleteById(id);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateStudent(String id, String paramater, String updatedValue){
        Student studentToBeUpdated = this.searchStudentById(id);
        if(studentToBeUpdated==null || updatedValue==null || paramater ==null){
            return false;
        }
        // if(paramater.equals("Name")){
        //     studentToBeUpdated.setName(updatedValue);
        // }
        switch(paramater){
            case "NAME": studentToBeUpdated.setName(updatedValue); break;
            case "ADDRESS": 
                studentToBeUpdated.setAddress(updatedValue); break;
            case "PHONENO": 
                if(this.checkPhone(updatedValue)==false) return false;
                studentToBeUpdated.setPhoneNo(updatedValue);
                break;
            case "EMAIL": 
                if(this.checkEmail(updatedValue)==false) return false;
                studentToBeUpdated.setEmail(updatedValue); 
                break;
            case "DOB": studentToBeUpdated.setDob(updatedValue); break;
            default: return false;
        }
        this.sri.update(studentToBeUpdated);
        return true;
    }

    // public void updateStudentForName(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newName = scanner.nextLine();
    //     studentToBeUpdated.setName(newName);
    // }

    // public void updateStudentForEmail(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newEmail = scanner.nextLine();
    //     studentToBeUpdated.setEmail(newEmail);
    // }

    // public void updateStudentForAddress(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newAddress = scanner.nextLine();
    //     studentToBeUpdated.setName(newAddress);
    // }

    // public void updateStudentForPhoneNo(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newPhoneno = scanner.nextLine();
    //     studentToBeUpdated.setPhoneNo(newPhoneno);
    // }

    // public void updateStudentForBloodGroup(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String bloodGroup = scanner.nextLine();
    //     studentToBeUpdated.setBloodGroup(bloodGroup);
    // }

    // public void updateStudentForDob(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newDob = scanner.nextLine();
    //     studentToBeUpdated.setDob(newDob);
    // }


    // =================

    // private List<Student> studentList;
    // private final StudentRepository sri;
    // private Set<String> studentIds = new HashSet<String>();

    // private boolean persistData(){
    //     return this.sri.saveData(this.studentList);
    // }

    // public StudentService(StudentRepository sri){
    //     this.sri = sri;
    //     this.studentList = sri.loadData();
    //     for(Student student: studentList){
    //         studentIds.add(student.getId());
    //     }
    // }

    // public boolean checkEmail(String email){

    //     if(email!= null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") == false){
    //         System.out.println("Something in Wmail is wrong");
    //          System.out.println(email);
    //          return false;
    //     }
    //     return true;
    // }

    // public boolean checkPhone(String phoneNo){
    //     if(phoneNo!= null && phoneNo.matches("\\d{10}$") == false){
    //         System.out.println("Something in PhoneNo is wrong");
    //         System.out.println(phoneNo);
    //         return false;
    //     }
    //     return true;
    //     // return student.getPhoneNo()!= null && student.getPhoneNo().matches("\\d{10}");
    // }

    // public boolean isUniqueId(String id){
    //     if(studentIds.contains(id)){
    //         System.out.println("Id already exists");
    //         return false;
    //     }
    //     return true;
    // }

    // public boolean addStudent(Student student){
    //     if(this.checkEmail(student.getEmail())==false) return false;
    //     if(this.checkPhone(student.getPhoneNo())==false) return false;
    //     if(this.isUniqueId(student.getId())==false) return false;
    //     if(this.searchStudentById(student.getId())==null){
    //         this.studentList.add(student);
    //         this.studentIds.add(student.getId());
    //         return this.persistData();
    //     }
    //     return false;
    // }

    // public List<Student> getStudentsList(){
    //     // return this.studentList;
    //     return Collections.unmodifiableList(this.studentList);
    // }

    // public Student searchStudentById(String id){
    //     for(Student student: this.studentList){
    //         if(student.getId().equals(id)){
    //             return student;
    //         }
    //     }
    //     return null;
    // }


    // public boolean deleteStudent(String id){
    //     Student studentTobeDeleted = this.searchStudentById(id);
    //     if(studentTobeDeleted == null){
    //         return false;
    //     }
    //     this.studentList.remove(studentTobeDeleted);
    //     this.studentIds.remove(studentTobeDeleted.getId());
    //     return this.persistData();
    // }

    // public boolean updateStudent(String id, String paramater, String updatedValue){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     if(studentToBeUpdated==null || updatedValue==null || paramater ==null){
    //         return false;
    //     }
    //     // if(paramater.equals("Name")){
    //     //     studentToBeUpdated.setName(updatedValue);
    //     // }
    //     switch(paramater){
    //         case "NAME": studentToBeUpdated.setName(updatedValue); break;
    //         case "ADDRESS": 
    //             studentToBeUpdated.setAddress(updatedValue); break;
    //         case "PHONENO": 
    //             if(this.checkPhone(updatedValue)==false) return false;
    //             studentToBeUpdated.setPhoneNo(updatedValue);
    //             break;
    //         case "EMAIL": 
    //             if(this.checkEmail(updatedValue)==false) return false;
    //             studentToBeUpdated.setEmail(updatedValue); 
    //             break;
    //         case "DOB": studentToBeUpdated.setDob(updatedValue); break;
    //         default: return false;
    //     }
    //     return this.persistData();
    // }

    // public void updateStudentForName(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newName = scanner.nextLine();
    //     studentToBeUpdated.setName(newName);
    // }

    // public void updateStudentForEmail(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newEmail = scanner.nextLine();
    //     studentToBeUpdated.setEmail(newEmail);
    // }

    //     public void updateStudentForAddress(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newAddress = scanner.nextLine();
    //     studentToBeUpdated.setName(newAddress);
    // }

    //     public void updateStudentForPhoneNo(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newPhoneno = scanner.nextLine();
    //     studentToBeUpdated.setPhoneNo(newPhoneno);
    // }

    //     public void updateStudentForBloodGroup(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String bloodGroup = scanner.nextLine();
    //     studentToBeUpdated.setBloodGroup(bloodGroup);
    // }

    //     public void updateStudentForDob(String id, Scanner scanner){
    //     Student studentToBeUpdated = this.searchStudentById(id);
    //     String newDob = scanner.nextLine();
    //     studentToBeUpdated.setDob(newDob);
    // }

}