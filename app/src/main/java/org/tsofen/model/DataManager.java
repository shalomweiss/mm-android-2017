package org.tsofen.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.tsofen.model.classes.User;

import java.util.Arrays;

/**
 * Created by minitour on 17/09/2017.
 */

public final class DataManager {

    /**
     * Singleton.
     */
    private static DataManager instance;
    public static DataManager getInstance(Context context){

        if (instance == null)
            instance = new DataManager(context);
        else
            instance.setContext(context);

        return instance;
    }

    /**
     * Shared Preferences object.
     */
    private SharedPreferences preferences;

    /**
     * Current Context.
     */
    private Context context;

    private DataManager(Context context){
        setContext(context);
        setSharedPreferences(context);
    }

    /**
     * Get the stored session token.
     * @return The session token if exists. Else null.
     */
    public String getToken(){
        return preferences.getString(Keys.TOKEN,null);
    }

    /**
     * Store the session token.
     * @param token The token received from the server.
     */
    public void setToken(String token){
        storeData(token,Keys.TOKEN);
    }

    /**
     * Methods that gets the current user stored on disk.
     * @return The current user.
     */
    public User getUser(){
       if(preferences.contains(Keys.CURRENT_USER)){
           String user = preferences.getString(Keys.CURRENT_USER, null);
           JsonParser parser = new JsonParser();
           JsonObject o = parser.parse(user).getAsJsonObject();

           return new User().init(o);
       }

       return null;
    }

    /**
     * Method that stores user object to disk.
     * @param user The user to store.
     */
    public void setUser(User user){
        String json = new Gson().toJson(user);
        storeData(json,Keys.CURRENT_USER);
    }

    /**
     * A method used to store the associated users as array.
     * @param users
     */
    public void associatedUsers(User[] users){
        String json = new Gson().toJson(Arrays.asList(users));
        storeData(json,Keys.ASSOCIATED_USERS);
    }

    /**
     * A method that returns the associated users.
     * @return A empty array if there are not associated users.
     */
    public User[] getAssociatedUsers(){
        try {
            if (preferences.contains(Keys.ASSOCIATED_USERS)) {
                String users = preferences.getString(Keys.ASSOCIATED_USERS, null);

                JsonParser parser = new JsonParser();
                JsonArray o = parser.parse(users).getAsJsonArray();

                User[] arr = new User[o.size()];

                for (int i = 0; i < o.size(); i++)
                    arr[i] = new User().init(o.get(i).getAsJsonObject());

                return arr;
            }
        }catch (Exception e){
            return new User[]{};
        }
        return new User[]{};
    }

    public void destroy(){
        preferences.edit().clear().apply();
    }

    /**
     * A method that is used to store data in shared preferences.
     *
     * @param o The data (value).
     *          Only accepts the following types: Boolean,Float,Integer,Long,String. As well as the primitive data types.
     *          Use `null` value to remove key.
     *
     * @param key The key that is used to access the data.
     */
    private void storeData(Object o,String key){
        SharedPreferences.Editor editor = preferences.edit();

        if (o == null) {
            editor.remove(key);
        }else {
            if (o instanceof Boolean)
                editor.putBoolean(key,(Boolean) o);

            if (o instanceof Float)
                editor.putFloat(key,(Float) o);

            if (o instanceof Integer)
                editor.putInt(key,(Integer) o);

            if (o instanceof Long)
                editor.putLong(key,(Long) o);

            if (o instanceof String)
                editor.putString(key, (String) o);
        }

        editor.apply();
    }

    /**
     * A method used to updating the context. Only used from `getInstance(context:)`
     * @param context
     */
    private void setContext(Context context) {
        this.context = context;
        setSharedPreferences(context);
    }

    /**
     * Recreate shared preferences from existing context.
     * @param context The new context.
     */
    private void setSharedPreferences(Context context) {
        preferences = context.getSharedPreferences(Keys.PREFERENCES, Context.MODE_PRIVATE);
    }

    private static class Keys {
        private static final String PREFERENCES = "MM_PREFERENCES";
        private static final String TOKEN = "TOKEN";
        private static final String FIRSTNAME = "FIRSTNAME";
        private static final String LASTNAME = "LASTNAME";
        private static final String ADDRESS = "ADDRESS";
        private static final String EMAIL = "EMAIL";
        private static final String PHONENUMBER = "PHONENUMBER";
        private static final String ID = "ID";

        private static final String ASSOCIATED_USERS = "ASSOCIATED_USERS";

        private static final String CURRENT_USER = "CURRENT_USER";
    }
}
