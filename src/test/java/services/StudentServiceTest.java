package services;

import org.junit.jupiter.api.Test;
import objects.Student;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void shouldNotAllowDuplicateStudentId() {

        // Arrange
        StudentService sf = new StudentService(new StudentFileService());
        sf.addStudent(new Student("099", "John Doe", "john.doe@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        // Act
        boolean result = sf.addStudent(new Student("099", "John Doe 2", "john.doe2@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        // Assert
        assertFalse(result);

    }


    @Test
    void shouldAllowNewStudentId() {

        // Arrange
        StudentService sf = new StudentService(new StudentFileService());
        // Act
        boolean result = sf.addStudent(new Student("1000", "John Doe 2", "john.doe2@example.com", "123 Main St, Anytown, USA", "1234567890", "A+", "1990-01-01"));

        // Assert
        assertTrue(result);

    }
}