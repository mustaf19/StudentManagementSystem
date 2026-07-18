import java.util.List;
import services.StudentService;
import objects.Student;


class Main{
    public static void main(String[] args){
        System.out.println("Hello World, application started");

        StudentService studentService = new StudentService();

        studentService.addStudent(new Student("1", "John Doe", "john.doe@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));
        studentService.addStudent(new Student("2", "Jane Smith", "jane.smith@example.com", "456 Elm St, Othertown, USA", "0987654321", "B-", "1991-02-02"));
        studentService.addStudent(new Student("3", "Jim Beam", "jim.beam@example.com", "789 Oak St, Anothertown, USA", "3456789012", "C+", "1992-03-03"));

        List<Student> students = studentService.getStudentsList();

        for(Student student : students){
            System.out.println(student.toString());
        }

        boolean isDeleted = studentService.deleteStudent("2");
        if(isDeleted){
            System.out.println("Student 2 deleted successfully");
        }else{
            System.out.println("Student 2 not found");
        }

        for(Student student : students){
            System.out.println(student.toString());
        }

        studentService.updateStudent("3", "Name", "Jimmy");

        System.out.println("After update");
                for(Student student : students){
            System.out.println(student.toString());
        }
    
    }
}