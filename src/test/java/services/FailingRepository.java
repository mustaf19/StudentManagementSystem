package services;

import java.util.List;
import java.util.ArrayList;
import objects.Student;

class FailingRepository implements StudentRepository{

    @Override
    public void save(Student student){
        throw new RuntimeException("Something went wrong!");
    }

    @Override
    public Student findById(String id){
        return null;
    }

    @Override
    public void deleteById(String id){
        throw new RuntimeException("Something went wrong!");
    }

    @Override
    public void update(Student student){
    }

    @Override
    public List<Student> findAll(){
        List<Student> li = new ArrayList<>();
        return li;
    }

    // @Override
    // public boolean saveData(List<Student> li){
    //     return false;
    // }

    // @Override
    // public List<Student> loadData(){
    //     List<Student> li = new ArrayList<>();
    //     return li;
    // }
}