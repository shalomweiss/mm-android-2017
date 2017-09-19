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
        void make(JsonObject json, Exception ex);
    }

    public interface Auth {
        void make(User user, String token, Exception ex);
    }

    public interface GetProfile {
        void make(User user, Exception ex);
    }

    public interface GetMeetings{
        void make(List<Meeting> meetingList,Exception ex);
    }

    public interface GetMeetingByID{
        void make(Meeting m,Exception ex);
    }
    public interface AddMeeting{
        void make(Meeting m,Exception ex);
    }
    public interface approveMeeting{
        void make(Meeting m,Exception ex);
    }
    public interface confirmMeeting{
        void make(Meeting m,Exception ex);
    }

    public interface UpdateProfile {
        void make(User user, Exception ex);
    }
}
