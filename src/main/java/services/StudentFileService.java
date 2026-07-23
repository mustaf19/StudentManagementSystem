package services;

import java.util.List;
import objects.Student;
import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;

public class StudentFileService{

    private static final String filePath = "studentData.json";

    public boolean saveData(List<Student> studentList){
        boolean result = true;

        ObjectMapper mapper = new ObjectMapper();
        try{
            String json = mapper.writeValueAsString(studentList);
            try(FileWriter writer = new FileWriter(filePath)){
                System.out.println(json);
                writer.write(json);
            }
            catch(IOException e){ e.printStackTrace(); result = false;}
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return result;
    }


    public List<Student> loadData(){

        FileReader reader = null;
        StringBuilder strBuild = new StringBuilder();
        String json = "";

        try{
            reader = new FileReader(filePath);
            int ch = reader.read();
            while(ch != -1){
                strBuild.append((char)ch);
                ch = reader.read();
            }
            json = strBuild.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(reader != null){
                try{
                    reader.close();
                }
                catch(IOException e){
                    
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Student> retStudent = new ArrayList<>();
        try{
            retStudent = mapper.readValue(json,new TypeReference<List<Student>>() {});
        }
        catch(JsonProcessingException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        return retStudent;

    }
}