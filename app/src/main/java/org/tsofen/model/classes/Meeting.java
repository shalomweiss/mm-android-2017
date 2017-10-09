package org.tsofen.model.classes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.tsofen.model.Mappable;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by win10 on 18/09/2017.
 */

public class Meeting implements Mappable<Meeting>{
    private String id;
    private String name;
    private String withMentee;
    private String type;
    /**
     *
     */
    private String at;
    private Calendar From;
    private Calendar To;
    private String subject;
    private String note;

    public Meeting(String id,String name,String type,String at,Calendar date,String subject,String note){
        setMeetingId(id);
        setName(name);
        setType(type);
        setAt(at);
        setFrom(date);
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
        if(obj.get("meetingWithMentee")!=null){
            setWithMentee(obj.get("meetingWithMentee").getAsString());
        }
        if(obj.get("meetingType")!=null){
            setName(obj.get("meetingType").getAsString());
        }
        if(obj.get("meetingAt")!=null){
            setName(obj.get("meetingAt").getAsString());
        }
        if(obj.get("meetingFrom")!=null){
           int mills=obj.get("meetingFrom").getAsInt();
           Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(mills);
            setFrom(cal);
        }
        if(obj.get("meetingTo")!=null){
            int mills=obj.get("meetingTo").getAsInt();
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(mills);
            setTo(cal);
        }
        if(obj.get("meetingSubject")!=null){
            setSubject(obj.get("meetingSubject").getAsString());
        }
        if(obj.get("meetingNote")!=null){
            setNote(obj.get("meetingNote").getAsString());
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

    public String getAt() {
        return at;
    }

    public Calendar getTo() {
        return To;
    }

    public String getId() {
        return id;
    }

    public String getWithMentee() {
        return withMentee;
    }
    public Calendar getFrom() {
        return From;
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

    public void setWithMentee(String withMentee) {
        this.withMentee = withMentee;
    }

    public void setTo(Calendar to) {
        To = to;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public void setFrom(Calendar from) {
        this.From = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Map<String, Object> map() {
        //TODO: create mappable object
        return null;
    }

    @Override
    public Meeting init(JsonObject o) {

        return this;
    }

    @Override
    public Meeting init(Map<String, Object> o) {
        
        return this;
    }

    static class Keys {
        public static final String ID = "meetingId";
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
