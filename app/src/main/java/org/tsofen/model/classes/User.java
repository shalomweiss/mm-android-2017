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
    private String type;
    private long joinDate = 0L;

    //mentee properties
    private String academicDicipline;
    private String academicDicipline2;//?
    private String remainingSemesters;
    private String academiclnstitution;
    private String graduationStatus;//?
    private String average;//?
    private boolean didSignEULA;

    //file urls
    private String resume;//?
    private String gradeSheet;//?

    //mentor properties
    private String exp;
    private String role;
    private String company;
    private String volunteering;
    private String workHistory;



    public User(){

    };

    private User(JsonElement o) {
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
        if(jsonUser.get("academicDicipline")!=null){
            setAcademicDicipline(jsonUser.get("academicDicipline").getAsString());
        }
        if(jsonUser.get("remainingSemesters")!=null){
            setRemainingSemesters(jsonUser.get("remainingSemesters").getAsString());
        }
        if(jsonUser.get("academiclnstitution")!=null){
            setAcademiclnstitution(jsonUser.get("academiclnstitution").getAsString());
        }
        if(jsonUser.get("type")!=null){
            setType(jsonUser.get("type").getAsString());
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
    public String getAcademicDicipline() { return this.academicDicipline; }
    public String getRemainingSemesters() { return this.remainingSemesters; }
    public String getAcademiclnstitution() { return this.academiclnstitution; }
    public String getGender() { return this.gender; }
    public String getType() { return this.type; }
    public String getSummary() { return this.summary; }
    public long getJoinedDate(){ return this.joinDate; }
    public long getJoinDate() {
        return joinDate;
    }
    public String getAcademicDicipline2() {
        return academicDicipline2;
    }
    public String getGraduationStatus() {
        return graduationStatus;
    }
    public String getAverage() {
        return average;
    }
    public boolean isDidSignEULA() {
        return didSignEULA;
    }
    public String getResume() {
        return resume;
    }
    public String getGradeSheet() {
        return gradeSheet;
    }
    public String getExp() {
        return exp;
    }
    public String getRole() {
        return role;
    }
    public String getCompany() {
        return company;
    }
    public String getVolunteering() {
        return volunteering;
    }
    public String getWorkHistory() {
        return workHistory;
    }

    public boolean isMentor(){
        return type.equalsIgnoreCase("MENTOR");
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

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
    public void setAcademicDicipline(String academicDicipline) { this.academicDicipline = academicDicipline; }
    public void setRemainingSemesters(String remainingSemesters) { this.remainingSemesters = remainingSemesters; }
    public void setAcademiclnstitution(String academiclnstitution) { this.academiclnstitution = academiclnstitution; }
    public void setType(String type) { this.type = type; }
    public void setGender(String gender) { this.gender = gender; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setJoinDate(Long joinDate) { this.joinDate = joinDate; }
    public void setJoinDate(long joinDate) {
        this.joinDate = joinDate;
    }
    public void setAcademicDicipline2(String academicDicipline2) {
        this.academicDicipline2 = academicDicipline2;
    }
    public void setGraduationStatus(String graduationStatus) {
        this.graduationStatus = graduationStatus;
    }
    public void setAverage(String average) {
        this.average = average;
    }
    public void setDidSignEULA(boolean didSignEULA) {
        this.didSignEULA = didSignEULA;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public void setGradeSheet(String gradeSheet) {
        this.gradeSheet = gradeSheet;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setVolunteering(String volunteering) {
        this.volunteering = volunteering;
    }
    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", summary='" + summary + '\'' +
                ", type='" + type + '\'' +
                ", joinDate=" + joinDate +
                ", academicDicipline='" + academicDicipline + '\'' +
                ", academicDicipline2='" + academicDicipline2 + '\'' +
                ", remainingSemesters='" + remainingSemesters + '\'' +
                ", academiclnstitution='" + academiclnstitution + '\'' +
                ", graduationStatus='" + graduationStatus + '\'' +
                ", average='" + average + '\'' +
                ", didSignEULA=" + didSignEULA +
                ", resume='" + resume + '\'' +
                ", gradeSheet='" + gradeSheet + '\'' +
                ", exp='" + exp + '\'' +
                ", role='" + role + '\'' +
                ", company='" + company + '\'' +
                ", volunteering='" + volunteering + '\'' +
                ", workHistory='" + workHistory + '\'' +
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
        hashedUser.put("type", type);
        hashedUser.put("joinDate", joinDate);
        hashedUser.put("academicDicipline", academicDicipline);
        hashedUser.put("academicDicipline2" , academicDicipline2);
        hashedUser.put("remainingSemesters", remainingSemesters);
        hashedUser.put("academiclnstitution", academiclnstitution);
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

        type = o.has("type") ? o.get("type").getAsString() : null;

        joinDate = o.has("joinDate") ? o.get("joinDate").getAsLong() : -1;

        academicDicipline = o.has("academicDicipline") ? o.get("academicDicipline").getAsString() : null;

        academicDicipline2 = o.has("academicDicipline2") ? o.get("academicDicipline2").getAsString() : null;

        remainingSemesters = o.has("remainingSemesters") ? o.get("remainingSemesters").getAsString() : null;

        academiclnstitution = o.has("academiclnstitution") ? o.get("academiclnstitution").getAsString() : null;

        graduationStatus = o.has("graduationStatus") ? o.get("graduationStatus").getAsString() : null;

        average = o.has("average") ? o.get("average").getAsString() : null;

        didSignEULA = o.has("didSignEULA") && o.get("didSignEULA").getAsBoolean();

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

        type = o.containsKey("type") ? o.get("type").toString() : null;

        joinDate = o.containsKey("joinDate") ? (long) o.get("joinDate") : -1;

        academicDicipline = o.containsKey("academicDicipline") ? o.get("academicDicipline").toString() : null;

        academicDicipline2 = o.containsKey("academicDicipline2") ? o.get("academicDicipline2").toString() : null;

        remainingSemesters = o.containsKey("remainingSemesters") ? o.get("remainingSemesters").toString() : null;

        academiclnstitution = o.containsKey("academiclnstitution") ? o.get("academiclnstitution").toString() : null;

        graduationStatus = o.containsKey("graduationStatus") ? o.get("graduationStatus").toString() : null;

        average = o.containsKey("average") ? o.get("average").toString() : null;

        didSignEULA = o.containsKey("didSignEULA") && (boolean) o.get("didSignEULA");

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