public class StudentFileService{

    private static final String filePath = "studentData.json";

    public boolean saveData(List<Student> studentList){
        boolean result = true;
        try(FileWriter writer = new FileWriter(filePath)){
            writer.write("Hello File");
        }
        catch(IOException e){
            result=false;
        }
        return result;
    }
}