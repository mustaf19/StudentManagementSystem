public class StudentFileService{

    private static final String filePath = "studentData.json";

    FileWriter writer = new FileWriter(filePath);
    writer.write(jsonData);
    save();

    load();
}