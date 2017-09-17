package org.tsofen.model;

import com.google.gson.JsonObject;

import org.tsofen.model.classes.User;

import java.io.IOException;

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
        void make(User user,String token,Exception ex);
    }
}
