package objects;

import java.time.LocalDate;

public class Student{
    private String id;
    private String name;
    private String email;
    private String address;
    private String phoneNo;
    private String bloodGroup;
    private LocalDate dob;

    public Student(){}
    
    public Student(String id, String name, String email, String address, String phoneNo, String bloodGroup, LocalDate dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public String getBloodGroup(){
        return this.bloodGroup;
    }

    public LocalDate getDob(){
        return this.dob;
    }

    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public void setAddress(String address){
        this.address = address;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

    public void setBloodGroup(String bloodGroup){
        this.bloodGroup = bloodGroup;
    }

    public void setDob(LocalDate dob){
        this.dob = dob;
    }      

    @Override
    public String toString(){
        return this.name + " " + this.id + "\n";
    }


    public String serializeDatatoJson(){
        return "{"+
        "\"Id\":\"" + this.id + "\"," +
         "\"Name\":\"" + this.name + "\"," +
          "\"PhoneNo\":\"" + this.phoneNo + "\"," +
           "\"email\":\"" + this.email + "\"," +
            "\"address\":\"" + this.address + "\"," +
             "\"bloodGroup\":\"" + this.bloodGroup + "\"," +
             "\"dob\":\"" + this.dob + "\""
        +"}";

    }
}