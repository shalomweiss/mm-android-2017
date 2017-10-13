package org.tsofen.model.classes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.tsofen.model.Mappable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by win10 on 18/09/2017.
 */

public class Meeting implements Mappable<Meeting>{
    private int meetingId;
    private int mentorId;
    private int menteeId;
    private String status;
    private String meetingType;
    private String subject;
    private String location;
    private long startingDate;
    private long endingDate;
    private boolean mentorComplete;
    private boolean menteeComplete;
    private String note;


    public Meeting(){}

    @Deprecated
    private Meeting(JsonElement o) {}


    public void setNote(String note) {
        this.note = note;
    }


    public String getMeetingTitle(boolean isMentor,User[] assoicated){
        if(isMentor){
            String name = null;
            for(int i = 0; i < assoicated.length; i++){
                if(getMenteeId() == assoicated[i].getId()){
                    name = assoicated[i].getFirstName() + " " + assoicated[i].getLastName();
                    break;
                }
            }
            return "Meeting with "+name;
        }else{
            return "Meeting with "+ assoicated[0].getFirstName() + " " + assoicated[0].getLastName();
        }
    }

    public int getStatusInt(){
        switch (status){
            case "PENDING":
                return 0;
            case "APPROVED":
                return 1;
            case "COMPLETE":
                return 2;
            default:
                return -1;
        }
    }

    public String getStartTime(){
        Date date = new Date(startingDate);
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
    }

    @Override
    public Map<String, Object> map() {
        //TODO: create mappable object
        return null;
    }

    @Override
    public Meeting init(JsonObject o) {
        meetingId = o.has("meetingId") ? o.get("meetingId").getAsInt() : -1;
        mentorId = o.has("mentorId") ? o.get("mentorId").getAsInt() : -1;
        menteeId = o.has("menteeId") ? o.get("menteeId").getAsInt() : -1;
        status = o.has("status") ? o.get("status").getAsString() : null;
        meetingType = o.has("meetingType") ? o.get("meetingType").getAsString() : null;
        subject = o.has("subject") ? o.get("subject").getAsString() : null;
        location = o.has("location") ? o.get("location").getAsString() : null;
        startingDate = o.has("startingDate") ? o.get("startingDate").getAsLong() : 0L;
        endingDate = o.has("endingDate") ? o.get("endingDate").getAsLong() : 0L;
        mentorComplete = o.has("mentorComplete") && o.get("mentorComplete").getAsBoolean();
        menteeComplete = o.has("menteeComplete") && o.get("menteeComplete").getAsBoolean();

        return this;
    }

    @Override
    public Meeting init(Map<String, Object> o) {

        return this;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(int menteeId) {
        this.menteeId = menteeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(long startingDate) {
        this.startingDate = startingDate;
    }

    public long getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(long endingDate) {
        this.endingDate = endingDate;
    }

    public boolean isMentorComplete() {
        return mentorComplete;
    }

    public void setMentorComplete(boolean mentorComplete) {
        this.mentorComplete = mentorComplete;
    }

    public boolean isMenteeComplete() {
        return menteeComplete;
    }

    public void setMenteeComplete(boolean menteeComplete) {
        this.menteeComplete = menteeComplete;
    }

    public String getNote() {
        return note;
    }


}
