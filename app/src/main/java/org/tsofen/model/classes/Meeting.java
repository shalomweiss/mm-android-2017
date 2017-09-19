package org.tsofen.model.classes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.security.Key;
import java.util.Calendar;

/**
 * Created by win10 on 18/09/2017.
 */

public class Meeting {
    private String id;
    private String name;
    private String type;
    private String location;
    private Calendar date;
    private String subject;
    private String note;

    public Meeting(String id,String name,String type,String location,Calendar date,String subject,String note){
        setMeetingId(id);
        setName(name);
        setType(type);
        setLocation(location);
        setDate(date);
        setSubject(subject);
        setNote(note);
    }

    public Meeting(JsonElement o) {
        JsonObject obj=o.getAsJsonObject();
        if(obj.get(Keys.ID)!=null){
            setMeetingId(obj.get(Keys.ID).getAsString());
        }
        if(obj.get("meetingName")!=null){
            setName(obj.get("meetingName").getAsString());
        }
        if(obj.get("meetingType")!=null){
            setName(obj.get("meetingType").getAsString());
        }
        if(obj.get("meetingLocation")!=null){
            setName(obj.get("meetingLocation").getAsString());
        }
        if(obj.get("meetingDate")!=null){
            setName(obj.get("meetingDate").getAsString());
        }
        if(obj.get("meetingSubject")!=null){
            setName(obj.get("meetingSubject").getAsString());
        }
        if(obj.get("meetingNote")!=null){
            setName(obj.get("meetingNote").getAsString());
        }
    }

    public String getMeetingId() {
        return id;
    }

    public void setMeetingId(String meetingId) {
        this.id = meetingId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public Calendar getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNote(String note) {
        this.note = note;
    }

    static class Keys {
        public static final String ID = "meetingId";
    }
}
