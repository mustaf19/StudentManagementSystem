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

public class StudentFileService implements StudentRepository{

    private static final String filePath = "studentData.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean saveData(List<Student> studentList){
        boolean result = true;

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

    @Override
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
            if(json.isBlank()){
                return new ArrayList<>();
            }
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
        
        List<Student> retStudent = new ArrayList<>();
        try{
            retStudent = mapper.readValue(json,new TypeReference<List<Student>>() {});
        }
        catch(JsonProcessingException e){e.printStackTrace();}
        // catch(IOException e){e.printStackTrace();}
        return retStudent;
    }
}