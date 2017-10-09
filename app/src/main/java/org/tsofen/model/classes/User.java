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

public class User implements Mappable<User>{

    //common
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String address;
    private String summary;
    private String status;
    private long joinDate = 0L;

    //mentee properties
    private String major;
    private String secondMajor;
    private String semesters;
    private String university;
    private String graduationStatus;
    private String average;
    private boolean didSignEULA;

    //file urls
    private String resume;
    private String gradeSheet;

    //mentor properties
    private String exp;
    private String role;
    private String company;
    private String volunteering;
    private String workHistory;



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
        hashedUser.put("id",id);
        hashedUser.put("firstName", firstName);
        hashedUser.put("lastName", lastName);
        hashedUser.put("email", email);
        hashedUser.put("phoneNumber", phoneNumber);
        hashedUser.put("gender", gender);
        hashedUser.put("address", address);
        hashedUser.put("summary", summary);
        hashedUser.put("status", status);
        hashedUser.put("joinDate", joinDate);
        hashedUser.put("major", major);
        hashedUser.put("secondMajor" , secondMajor);
        hashedUser.put("semesters", semesters);
        hashedUser.put("university", university);
        hashedUser.put("graduationStatus", graduationStatus);
        hashedUser.put("average", average);
        hashedUser.put("didSignEULA", didSignEULA);
        hashedUser.put("resume", resume);
        hashedUser.put("gradeSheet", gradeSheet);
        hashedUser.put("exp", exp);
        hashedUser.put("role", role);
        hashedUser.put("company", company);
        hashedUser.put("volunteering", volunteering);
        hashedUser.put("workHistory", workHistory);
        
        return hashedUser;
    }

    @Override
    public User init(JsonObject o) {
        //TODO: init properties
        //property = exists ? realValue : defaultValue

        id = o.has("id") ? o.get("id").getAsInt() : -1;
        firstName = o.has("firstName") ? o.get("firstName").getAsString() : null;

        lastName = o.has("lastName") ? o.get("lastName").getAsString() : null;

        email = o.has("email") ? o.get("email").getAsString() : null;

        phoneNumber = o.has("phoneNumber") ? o.get("phoneNumber").getAsString() : null;

        gender = o.has("gender") ? o.get("gender").getAsString() : null;

        address = o.has("address") ? o.get("address").getAsString() : null;

        summary = o.has("summary") ? o.get("summary").getAsString() : null;

        status = o.has("status") ? o.get("status").getAsString() : null;

        joinDate = o.has("joinDate") ? o.get("joinDate").getAsLong() : -1;

        major = o.has("major") ? o.get("major").getAsString() : null;

        secondMajor = o.has("secondMajor") ? o.get("secondMajor").getAsString() : null;

        semesters = o.has("semesters") ? o.get("semesters").getAsString() : null;

        university = o.has("university") ? o.get("university").getAsString() : null;

        graduationStatus = o.has("graduationStatus") ? o.get("graduationStatus").getAsString() : null;

        average = o.has("average") ? o.get("average").getAsString() : null;

        didSignEULA = o.has("didSignEULA") ? o.get("didSignEULA").getAsBoolean() : null;

        resume = o.has("resume") ? o.get("resume").getAsString() : null;

        gradeSheet = o.has("gradeSheet") ? o.get("gradeSheet").getAsString() : null;

        exp = o.has("exp") ? o.get("exp").getAsString() : null;

        role = o.has("role") ? o.get("role").getAsString() : null;

        company = o.has("company") ? o.get("company").getAsString() : null;

        volunteering = o.has("volunteering") ? o.get("volunteering").getAsString() : null;

        workHistory = o.has("workHistory") ? o.get("workHistory").getAsString() : null;




        return this;
    }

    @Override
    public User init(Map<String, Object> o) {
        //TODO: init properties

        id = o.containsKey("id") ? (int) o.get("id") : -1;

        firstName = o.containsKey("firstName") ? o.get("firstName").toString() : null;

        lastName = o.containsKey("lastName") ? o.get("lastName").toString() : null;

        email = o.containsKey("email") ? o.get("email").toString() : null;

        phoneNumber = o.containsKey("phoneNumber") ? o.get("phoneNumber").toString() : null;

        gender = o.containsKey("gender") ? o.get("gender").toString() : null;

        address = o.containsKey("address") ? o.get("address").toString() : null;

        summary = o.containsKey("summary") ? o.get("summary").toString() : null;

        status = o.containsKey("status") ? o.get("status").toString() : null;

        joinDate = o.containsKey("joinDate") ? (long) o.get("joinDate") : -1;

        major = o.containsKey("major") ? o.get("major").toString() : null;

        secondMajor = o.containsKey("secondMajor") ? o.get("secondMajor").toString() : null;

        semesters = o.containsKey("semesters") ? o.get("semesters").toString() : null;

        university = o.containsKey("university") ? o.get("university").toString() : null;

        graduationStatus = o.containsKey("graduationStatus") ? o.get("graduationStatus").toString() : null;

        average = o.containsKey("average") ? o.get("average").toString() : null;

        didSignEULA = o.containsKey("didSignEULA") ? (boolean) o.get("didSignEULA") : null;

        resume = o.containsKey("resume") ? o.get("resume").toString() : null;

        gradeSheet = o.containsKey("gradeSheet") ? o.get("gradeSheet").toString() : null;

        exp = o.containsKey("exp") ? o.get("exp").toString() : null;

        role = o.containsKey("role") ? o.get("role").toString() : null;

        company = o.containsKey("company") ? o.get("company").toString() : null;

        volunteering = o.containsKey("volunteering") ? o.get("volunteering").toString() : null;

        workHistory = o.containsKey("workHistory") ? o.get("workHistory").toString() : null;

        return this;
    }
}