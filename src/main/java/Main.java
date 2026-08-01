import java.util.List;
import services.StudentService;
import services.StudentFileService;
import objects.Student;
import java.util.Scanner;
import java.util.UUID;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.JsonProcessingException;


class Main{
    private static void showMenu(){
        System.out.println("1. add\n2. delete\n3.update\n4.get Students\n and above exit");

    }

    private static void addStudent(Scanner scanner, StudentService service){
        String name = scanner.nextLine();
        // Random random = new Random();
        String uuid = UUID.randomUUID().toString();
        service.addStudent(new Student(uuid,name,"jim.beam@example.com", "789 Oak St, Anothertown, USA", "3456789012", "C+", "1992-03-03" ));
    }

    private static void deleteStudent(Scanner scanner, StudentService service){
        String id = scanner.nextLine();
        service.deleteStudent(id);
    }

    private static void updateStudent(Scanner scanner, StudentService service){

    }

    private static void showStudents(StudentService service){
        List<Student> students = service.getStudentsList();
        for(Student student: students){
            System.out.println(student.toString());
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService(new StudentFileService());

        studentService.addStudent(new Student("1", "John Doe", "john.doe@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));
        studentService.addStudent(new Student("2", "Jane Smith", "jane.smith@example.com", "456 Elm St, Othertown, USA", "0987654321", "B-", "1991-02-02"));
        studentService.addStudent(new Student("3", "Jim Beam", "jim.beam@example.com", "789 Oak St, Anothertown, USA", "3456789012", "C+", "1992-03-03"));


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

        boolean isDeleted = studentService.deleteStudent("2");
        if(isDeleted){
            System.out.println("Student 2 deleted successfully");
        }else{
            System.out.println("Student 2 not found");
        }

        // for(Student student : students){
        //     System.out.println(student.toString());
        // }

        studentService.updateStudent("3", "Name", "Jimmy");

        scanner.close();
    
    }
}