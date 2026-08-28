package services;

import org.junit.jupiter.api.Test;
import objects.Student;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Test
    void shouldNotAllowDuplicateStudentId() {

        // Arrange
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        sf.addStudent(new Student("099", "John Doe", "john.doe@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        // Act
        boolean result = sf.addStudent(new Student("099", "John Doe 2", "john.doe2@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        // Assert
        assertFalse(result);

    }


    @Test
    void shouldAllowNewStudentId() {

        // Arrange
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        // Act
        boolean result = sf.addStudent(new Student("1000", "John Doe 2", "john.doe2@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        // Assert
        assertTrue(result);

    }


     @Test
    void shouldFindExistingStudentById() {

        // Arrange
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        // Act
        boolean result = sf.addStudent(new Student("1000", "John Doe 2", "john.doe2@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        Student st = sf.searchStudentById("1000");

        // Assert
        assertEquals("1000", st.getId());

    }

    @Test
    void shouldReturnNullWhenStudentDoesNotExist(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        Student st = sf.searchStudentById("10000");

        assertNull(st);
    }

    @Test
    void deletedStudentShouldNotBePresent(){
        // Arrange
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        boolean addResult = sf.addStudent(new Student("1005", "Juhnny Eng", "john.eng@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));


        // Act
        boolean result = sf.deleteStudent("1005");
        Student st = sf.searchStudentById("1005");


        // Assert
        assertTrue(addResult);
        assertTrue(result);
        assertNull(st);
        
    }


    @Test
    void deletingNonExistingStudent(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean result = sf.deleteStudent("10000");

        assertFalse(result);
    }


    @Test
    void updateExistingStudent(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        sf.addStudent(new Student("1006", "JOhn A", "john.eng@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        boolean rs = sf.updateStudent("1006", "NAME", "John Updated");

        assertTrue(rs);
        assertEquals("John Updated", sf.searchStudentById("1006").getName());
    }

    @Test
    void updateExistingStudentWithWrongFormatEmail(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        sf.addStudent(new Student("2001", "JOhn B", "john.eng@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        boolean rs = sf.updateStudent("2001", "EMAIL", "john.engexample.com");

        assertFalse(rs);
        assertEquals("john.eng@example.com", sf.searchStudentById("2001").getEmail());
    }

    @Test
    void updateExistingStudentWithWrongFormatPhoneNo(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        sf.addStudent(new Student("2002", "JOhn Eng", "john.eng@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        boolean rs = sf.updateStudent("2002", "PHONENO", "123456");

        assertFalse(rs);
        assertEquals("1234567890", sf.searchStudentById("2002").getPhoneNo());
    }

    @Test
    void updatingNonExistingParameter(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());
        sf.addStudent(new Student("2002", "JOhn Eng", "john.eng@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        boolean rs = sf.updateStudent("2002", "sdsdv", "csdvsr");

        assertFalse(rs);
    }

    
    @Test
    void updateNonExistingStudent(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean rs = sf.updateStudent("1007", "NAME", "John");

        assertFalse(rs);
    }


    @Test
    void checkCorrectEmail(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean result = sf.checkEmail("john.doe@gmail.com");

        assertTrue(result);
    }

    @Test
    void checkInCorrectEmail(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean result = sf.checkEmail("john.doegmail.com");

        assertFalse(result);
    }

    @Test
    void checkCorrectPhoneNo(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean result = sf.checkPhone("9876543210");

        assertTrue(result);
    }

    @Test
    void checkInCorrectPhoneNo(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean result = sf.checkPhone("9876");

        assertFalse(result);
    }

    @Test
    void addingWithInvalidPhone(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean rs = sf.addStudent(new Student("1007", "JOhn Eng", "john.eng@example.com", "123 Main St, Anytown, USA", "12345", "A+", "1990-01-01"));

        assertFalse(rs);
    }

    @Test
    void addingWithInvalidEmail(){
        StudentService sf = new StudentService(new InMemoryStudentRepository());

        boolean rs = sf.addStudent(new Student("1008", "JOhn Eng", "john.engexample.com", "123 Main St, Anytown, USA", "9087654321", "A+", "1990-01-01"));

        assertFalse(rs);
    }

    @Test
    void addingToFailingSaveData(){
        StudentService sf = new StudentService(new FailingRepository());

        boolean rs = sf.addStudent(new Student("1009", "JOhn Eng", "john.eng@example.com", "123 Main St, Anytown, USA", "9087654321", "A+", "1990-01-01"));

        assertFalse(rs);
    }

    @Test
    void shouldPersistWhenStudentIsAdded() {

        // Arrange
        StudentRepository repository = mock(StudentRepository.class);

        when(repository.findAll()).thenReturn(new ArrayList<>());

        StudentService sf = new StudentService(repository);

        // Act
        sf.addStudent(new Student("1010", "Summer Von", "sum.von@example.com", "123 Main St, Anytown, USA", "9087654321", "A+", "1990-01-01"));


        // sf.addStudent(student);

        // Verify
        verify(repository).save(any());

    }
}