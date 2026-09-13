package services;

import java.util.*;
import objects.Student;
import java.sql.Connection;
import java.sql.DriverManager;


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
        String sql = "INSERT INTO students "

    }

    @Override
    public Student findById(String id){
        return null;
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
}