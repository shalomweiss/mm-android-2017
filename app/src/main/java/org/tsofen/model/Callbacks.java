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
    @FunctionalInterface
    interface Inner{
        void make(JsonObject json, Exception exception);
    }
    /**
     * General callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface General{
        void make(ServerResponse response,Exception exception);
    }
    /**
     * Auth callback, used for authentication
     */
    @FunctionalInterface
    public interface Auth {
        void make(ServerResponse response, User user, String token, Exception exception);
    }
    /**
     * GetProfile callback, used when fetching a user object.
     */
    @FunctionalInterface
    public interface GetProfile {
        void make(ServerResponse response,User user, Exception exception);
    }
    /**
     * GetMeetings callback, used to get a list of meetings.
     */
    @FunctionalInterface
    public interface GetMeetings{
        void make(ServerResponse response,List<Meeting> meetingList,Exception exception);
    }
    /**
     * GetMeetingByID callback, used to get a specific meeting object.
     */
    @FunctionalInterface
    public interface GetMeetingByID{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * AddMeeting callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface AddMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * ApproveMeeting callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface ApproveMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * ConfirmMeeting callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface ConfirmMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }
    /**
     * UpdateProfile callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface UpdateProfile {
        void make(ServerResponse response,User user, Exception ex);
    }
    /**
     * GetMentees callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface GetMentees {
        void make(ServerResponse response,List<User> users, Exception ex);
    }
    /**
     * GetMentor callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface GetMentor {
        void make(ServerResponse response,User user, Exception ex);
    }

    @FunctionalInterface
    public interface GetAssociatedUsers{
        void make(ServerResponse response,User[] users, Exception ex);
    }
}
