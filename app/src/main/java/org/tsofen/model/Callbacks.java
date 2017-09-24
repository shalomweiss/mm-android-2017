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

    /**
     * Inner callback, used only by the API Manager internally.
     */
    interface Inner{
        void make(JsonObject json, Exception exception);
    }

    public interface General{
        void make(ServerResponse response,Exception exception);
    }

    public interface Auth {
        void make(ServerResponse response, User user, String token, Exception exception);
    }

    public interface GetProfile {
        void make(ServerResponse response,User user, Exception exception);
    }

    public interface GetMeetings{
        void make(ServerResponse response,List<Meeting> meetingList,Exception exception);
    }

    public interface GetMeetingByID{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }

    public interface AddMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }

    public interface ApproveMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }

    public interface ConfirmMeeting{
        void make(ServerResponse response,Meeting meeting,Exception exception);
    }

    public interface UpdateProfile {
        void make(ServerResponse response,User user, Exception ex);
    }
}
