package services;

import java.util.*;
import objects.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.Student;
import exceptions.RepositoryException;


public class JdbcStudentRepository implements StudentRepository {

    private final Connection connection;

    public JdbcStudentRepository(Connection connection){
        this.connection=connection;
        createTableIfNotExist();
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
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RepositoryException("Could not create students table", e);
        }
    }

    @Override
    public void save(Student student){
        String sql = "INSERT INTO students (id, name, email, address, phone_no, blood_group, dob) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            bindStudent(ps, student, false);
            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new RepositoryException("Student didn't save!", e);
        }

    }

    @Override
    public Student findById(String id){
        String sql = "SELECT * FROM students where id=?;";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRowToStudent(rs);
                }
                return null;
            }
            catch(SQLException e){
                throw new RepositoryException("Student could not be found!"+id, e);
            }
        }
    }

    @Override
    public void deleteById(String id){

    }

    @Override
    public void update(Student student){

    }

    @Override
    public List<Student> findAll(){
        return new ArrayList<Student>();
    }

    private Student mapRowToStudent(ResultSet rs) throws SQLException{
        String dobText = rs.getString("dob");
        LocalDate dob = (dobText==null || dobText.isBlank()) ? null:LocalDate.parse(dobText);
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