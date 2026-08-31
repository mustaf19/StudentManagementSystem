import java.util.List;
import services.StudentService;
import services.StudentFileService;
import objects.Student;
import java.util.Scanner;
import java.util.UUID;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;


public class Main{
    private static void showMenu(){
        System.out.println("1. add\n2. delete\n3.update\n4.get Students\n and above exit");

    }

    private static void addStudent(Scanner scanner, StudentService service){
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        // Random random = new Random();
        String uuid = UUID.randomUUID().toString();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String number = scanner.nextLine();
        if(service.addStudent(new Student(uuid,name,email, "789 Oak St, Anothertown, USA", number, "C+", "1992-03-03" ))==true){
            System.out.println("Student Added successfully!");
        }
        else{
            System.out.println("Student Added Failed!");
        }
    }

    private static void deleteStudent(Scanner scanner, StudentService service){
        String id = scanner.nextLine();
        service.deleteStudent(id);
    }

    private static void updateStudent(Scanner scanner, StudentService service){

        System.out.println("Enter id of student to be updated");
        String id = scanner.nextLine();
        Student student = service.searchStudentById(id);
        if(student == null){
            System.out.println("Student not found");
        }
        else{
            System.out.println("Current name: " + student.getName());
            System.out.println("Enter name to be changed");
            String name = scanner.nextLine();
            if(service.updateStudent(id, "NAME", name)==true){
                System.out.println("Student updated successfully!");
            }
            else{
                System.out.println("Student did not updated!");
            }
            System.out.println(student);
        }
    }

    private static void showStudents(StudentService service){
        List<Student> students = service.getStudentsList();
        if(students.isEmpty()){
            System.out.println("No records found!");
            return;
        }
        for(Student student: students){
            System.out.println(student);
        }
    }

    // private static void searchStudent(Scanner scanner, StudentService service){
    //     System.out.println("Enter id of the student");
    //     String id = scanner.nextLine();
    //     Studnet searchedStudent = service.searchStudentById(id);
    //     if(searchedStudent!=null)
    //         System.out.println(searchedStudent.toString());
    //     else System.out.println("No student found with this id");
    // }

    private static void searchStudent(Scanner scanner, StudentService service){
        System.out.println("Enter id of the student");
        String id = scanner.nextLine();
        Student searchedStudent = service.searchStudentById(id);
        if(searchedStudent!=null)
            System.out.println(searchedStudent.toString());
        else System.out.println("No student found with this id");
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService(new StudentFileService());

        // studentService.addStudent(new Student("1", "John Doe", "john.doe@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));
        // studentService.addStudent(new Student("2", "Jane Smith", "jane.smith@example.com", "456 Elm St, Othertown, USA", "0987654321", "B-", "1991-02-02"));
        // studentService.addStudent(new Student("3", "Jim Beam", "jim.beam@example.com", "789 Oak St, Anothertown, USA", "3456789012", "C+", "1992-03-03"));

        while(true){
            // this.showMenu(); (this is used for objects and static class dont require this, as they dont have objects)
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1: addStudent(scanner, studentService); break;
                case 2: deleteStudent(scanner, studentService); break;
                case 3: updateStudent(scanner, studentService); break;
                case 4: showStudents(studentService); break;
                default: break;
            }
            if(choice==0){
                break;
            }
        }


        // System.out.println("Hello World, application started");
        // StudentService studentService = new StudentService(new StudentFileService());

        // List<Student> students = studentService.getStudentsList();

        // for(Student student : students){
        //     System.out.println(student.toString());
        // }

        // boolean isDeleted = studentService.deleteStudent("2");
        // if(isDeleted){
        //     System.out.println("Student 2 deleted successfully");
        // }else{
        //     System.out.println("Student 2 not found");
        // }

        // for(Student student : students){
        //     System.out.println(student.toString());
        // }

        // studentService.updateStudent("3", "Name", "Jimmy");

        scanner.close();
    
    }
}