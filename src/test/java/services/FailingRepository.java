package services;

import java.util.List;
import java.util.ArrayList;
import objects.Student;

class FailingRepository implements StudentRepository{

    @Override
    public boolean saveData(List<Student> li){
        return false;
    }

    @Override
    public List<Student> loadData(){
        List<Student> li = new ArrayList<>();
        return li;
    }
}