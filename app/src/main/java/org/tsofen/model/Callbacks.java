package org.tsofen.model;

import com.google.gson.JsonObject;

import org.tsofen.model.classes.Meeting;
import org.tsofen.model.classes.User;

import java.io.IOException;
import java.util.List;
/**
 * Created by minitour on 13/09/2017.
 */
public final class Callbacks {

    private Callbacks(){}

    /**
     * Inner callback, used only by the API Manager internally.
     */
    interface Inner{
        void make(JsonObject json, Exception exception);
    }
    /**
     * General callback, used only by the API Manager internally.
     */
    public interface General{
        void make(ServerResponse response,Exception exception);
    }
    /**
     * Auth callback, used for authentication
     */
    public interface Auth {
        void make(ServerResponse response, User user, String token, Exception exception);
    }
    /**
     * GetProfile callback, used when fetching a user object.
     */
    public interface GetProfile {
        void make(ServerResponse response,User user, Exception exception);
    }
    /**
     * GetMeetings callback, used to get a list of meetings.
     */
    public interface GetMeetings{
        void make(ServerResponse response,List<Meeting> meetingList,Exception exception);
    }
    /**
     * GetMeetingByID callback, used to get a specific meeting object.
     */
    public interface GetMeetingByID{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * AddMeeting callback, used only by the API Manager internally.
     */
    public interface AddMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * ApproveMeeting callback, used only by the API Manager internally.
     */
    public interface ApproveMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * ConfirmMeeting callback, used only by the API Manager internally.
     */
    public interface ConfirmMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * UpdateProfile callback, used only by the API Manager internally.
     */
    public interface UpdateProfile {
        void make(ServerResponse response,User user, Exception ex);
    }
    /**
     * GetMentees callback, used only by the API Manager internally.
     */
    public interface GetMentees {
        void make(ServerResponse response,List<User> users, Exception ex);
    }
    /**
     * GetMentor callback, used only by the API Manager internally.
     */
    public interface GetMentor {
        void make(ServerResponse response,User user, Exception ex);
    }
}
