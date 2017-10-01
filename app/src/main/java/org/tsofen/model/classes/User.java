package org.tsofen.model.classes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.tsofen.model.Mappable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by minitour on 13/09/2017.
 */

public class User implements Mappable{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String major;
    private String semesters;
    private String university;
    private String status;
    private String gender;
    private String summary;
    private long joinDate = 0L;

    public User(){

    };

    public Map<String,Object> getHashedUser(){
        Map<String,Object> hashedUser = new HashMap<String,Object>();
        hashedUser.put("firstName", firstName);
        hashedUser.put("lastName", lastName);
        hashedUser.put("email", email);
        hashedUser.put("phoneNumber", phoneNumber);
        hashedUser.put("address", address);
        hashedUser.put("major", major);
        hashedUser.put("semesters", semesters);
        hashedUser.put("university", university);
        hashedUser.put("status", status);
        hashedUser.put("gender", gender);
        hashedUser.put("summary", summary);
        hashedUser.put("joinDate", joinDate);
        return hashedUser;
    };

    public User(JsonElement o) {
        JsonObject jsonUser=o.getAsJsonObject();
        if(jsonUser.get("id")!=null){
            setId(jsonUser.get("id").getAsInt());
        }
        if(jsonUser.get("firstName")!=null){
            setFirstName(jsonUser.get("firstName").getAsString());
        }
        if(jsonUser.get("lastName")!=null){
            setLastName(jsonUser.get("lastName").getAsString());
        }
        if(jsonUser.get("email")!=null){
            setEmail(jsonUser.get("email").getAsString());
        }
        if(jsonUser.get("phoneNumber")!=null){
            setPhoneNumber(jsonUser.get("phoneNumber").getAsString());
        }
        if(jsonUser.get("address")!=null){
            setAddress(jsonUser.get("address").getAsString());
        }
        if(jsonUser.get("major")!=null){
            setMajor(jsonUser.get("major").getAsString());
        }
        if(jsonUser.get("semesters")!=null){
            setSemesters(jsonUser.get("semesters").getAsString());
        }
        if(jsonUser.get("university")!=null){
            setUniversity(jsonUser.get("university").getAsString());
        }
        if(jsonUser.get("status")!=null){
            setStatus(jsonUser.get("status").getAsString());
        }
        if(jsonUser.get("gender")!=null){
            setGender(jsonUser.get("gender").getAsString());
        }
        if(jsonUser.get("summary")!=null) {
            setSummary(jsonUser.get("summary").getAsString());
        }
        if(jsonUser.get("joinDate")!=null) {
            setJoinDate(jsonUser.get("joinDate").getAsLong());
        }

    }

    public int getId(){
        return this.id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getAddress(){
        return this.address;
    }
    public String getMajor() { return this.major; }
    public String getSemesters() { return this.semesters; }
    public String getUniversity() { return this.university; }
    public String getGender() { return this.gender; }
    public String getStatus() { return this.status; }
    public String getSummary() { return this.summary; }
    public long getJoinedDate(){ return this.joinDate; }



    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setMajor(String major) { this.major = major; }
    public void setSemesters(String semesters) { this.semesters = semesters; }
    public void setUniversity(String university) { this.university = university; }
    public void setStatus(String status) { this.status = status; }
    public void setGender(String gender) { this.gender = gender; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setJoinDate(Long joinDate) { this.joinDate = joinDate; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", major='" + major + '\'' +
                ", semesters='" + semesters + '\'' +
                ", university='" + university + '\'' +
                ", status='" + status + '\'' +
                ", gender='" + gender + '\'' +
                ", summary='" + summary + '\'' +
                ", joinDate=" + convetLongToDate(joinDate)  +
                '}';
    }

    public String convetLongToDate(long time){
        long joinedDateLong = getJoinedDate();
        Date date = new Date(joinedDateLong);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(date);
    }

    @Override
    public Map<String, Object> map() {
        Map<String,Object> hashedUser = new HashMap<String,Object>();
        hashedUser.put("firstName", firstName);
        hashedUser.put("lastName", lastName);
        hashedUser.put("email", email);
        hashedUser.put("phoneNumber", phoneNumber);
        hashedUser.put("address", address);
        hashedUser.put("major", major);
        hashedUser.put("semesters", semesters);
        hashedUser.put("university", university);
        hashedUser.put("status", status);
        hashedUser.put("gender", gender);
        hashedUser.put("summary", summary);
        hashedUser.put("joinDate", joinDate);
        return hashedUser;
    }

    @Override
    public void init(JsonObject o) {

    }

    @Override
    public void init(Map<String, Object> o) {

    }
}
