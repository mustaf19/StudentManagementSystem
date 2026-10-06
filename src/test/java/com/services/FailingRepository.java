package com.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.exceptions.RepositoryException;
import com.objects.Student;

class FailingRepository implements StudentRepository{

    @Override
    public void save(Student student){
        throw new RuntimeException("Something went wrong!");
    }

    @Override
    public Optional<Student> findById(String id){
        return Optional.empty();
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

    @Override
    public List<Student> findPage(int limit, int offset){
        throw new RuntimeException("Something went wrong!");
    }

    @Override 
    public long count(){
        throw new RepositoryException("SQL error", "SQL error");
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