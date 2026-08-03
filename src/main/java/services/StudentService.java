package services;

import java.util.List;
import java.util.ArrayList;
import objects.Student;
import java.util.Collections;
// import services.StudentFileService; // same package doesnt require importing

public class StudentService{

    private List<Student> studentList;
    private final StudentFileService sfs;

    private void persistData(){
        this.sfs.saveData(this.studentList);
    }

    public StudentService(StudentFileService sfs){
        this.sfs = sfs;
        this.studentList = sfs.loadData();
    }

    public boolean addStudent(Student student){
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

    public void updateStudentForName(String id, Scanner scanner){
        Student studentToBeUpdated = this.searchStudentById(id);
        String newName = scanner.nextLine();
        studentToBeUpdated.setName(newName);
    }

    public void updateStudentForEmail(String id, Scanner scanner){
        Student studentToBeUpdated = this.searchStudentById(id);
        String newEmail = scanner.nextLine();
        studentToBeUpdated.setEmail(newEmail);
    }

        public void updateStudentForAddress(String id, Scanner scanner){
        Student studentToBeUpdated = this.searchStudentById(id);
        String newAddress = scanner.nextLine();
        studentToBeUpdated.setName(newAddress);
    }

        public void updateStudentForPhoneNo(String id, Scanner scanner){
        Student studentToBeUpdated = this.searchStudentById(id);
        String newPhoneno = scanner.nextLine();
        studentToBeUpdated.setPhoneNo(newPhoneno);
    }

        public void updateStudentForBloodGroup(String id, Scanner scanner){
        Student studentToBeUpdated = this.searchStudentById(id);
        String bloodGroup = scanner.nextLine();
        studentToBeUpdated.setBloodGroup(bloodGroup);
    }

        public void updateStudentForDob(String id, Scanner scanner){
        Student studentToBeUpdated = this.searchStudentById(id);
        String newDob = scanner.nextLine();
        studentToBeUpdated.setDob(newDob);
    }

}