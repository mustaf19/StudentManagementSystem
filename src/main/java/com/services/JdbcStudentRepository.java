package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.exceptions.RepositoryException;
import com.objects.Student;

@Repository
public class JdbcStudentRepository implements StudentRepository {

    public JdbcStudentRepository() {
        createTableIfNotExist();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:student.db");
    }

    private void createTableIfNotExist() {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
            + "id TEXT PRIMARY KEY,"
            + "name TEXT,"
            + "email TEXT,"
            + "address TEXT,"
            + "phone_no TEXT,"
            + "blood_group TEXT,"
            + "dob TEXT"
            + ")";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RepositoryException(
                "Could not create students table", e
            );
        }
    }

    @Override
    public void save(Student student) {
        String sql =
            "INSERT INTO students " +
            "(id, name, email, address, phone_no, blood_group, dob) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            bindStudent(ps, student, false);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(
                "Student didn't save!", e
            );
        }
    }

    @Override
    public Optional<Student> findById(String id) {
        String sql = "SELECT * FROM students WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToStudent(rs));
                }

                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RepositoryException(
                "Student could not be found! " + id, e
            );
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(
                "Delete operation didn't perform at id: " + id, e
            );
        }
    }

    @Override
    public void update(Student student) {
        String sql =
            "UPDATE students SET " +
            "name=?, email=?, address=?, phone_no=?, " +
            "blood_group=?, dob=? " +
            "WHERE id=?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            bindStudent(ps, student, true);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(
                "Could not update student " + student.getId(), e
            );
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                students.add(mapRowToStudent(rs));
            }

            return students;

        } catch (SQLException e) {
            throw new RepositoryException(
                "Students could not be found!", e
            );
        }
    }

    private void bindStudent(
        PreparedStatement ps,
        Student student,
        boolean update
    ) throws SQLException {

        if (update) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getPhoneNo());
            ps.setString(5, student.getBloodGroup());
            ps.setString(6, dobToText(student.getDob()));
            ps.setString(7, student.getId());
        } else {
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getPhoneNo());
            ps.setString(6, student.getBloodGroup());
            ps.setString(7, dobToText(student.getDob()));
        }
    }

    private String dobToText(LocalDate ld) {
        return ld == null ? null : ld.toString();
    }

    private Student mapRowToStudent(ResultSet rs) throws SQLException {
        String dobText = rs.getString("dob");

        LocalDate dob =
            (dobText == null || dobText.isBlank())
                ? null
                : LocalDate.parse(dobText);

        return new Student(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("address"),
            rs.getString("phone_no"),
            rs.getString("blood_group"),
            dob
        );
    }
}
