package org.tsofen.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.tsofen.model.classes.User;

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
        User user = new User();
        user.setFirstName(preferences.getString(Keys.FIRSTNAME,null));
        user.setLastName(preferences.getString(Keys.LASTNAME,null));
        user.setAddress(preferences.getString(Keys.ADDRESS,null));
        user.setEmail(preferences.getString(Keys.EMAIL,null));
        user.setPhoneNumber(preferences.getString(Keys.PHONENUMBER,null));
        user.setId(preferences.getInt(Keys.ID, 0));
        return user;
    }

    /**
     * Method that stores user object to disk.
     * @param user The user to store.
     */
    public void setUser(User user){
        storeData(user.getFirstName(),Keys.FIRSTNAME);
        storeData(user.getLastName(),Keys.LASTNAME);
        storeData(user.getAddress(),Keys.ADDRESS);
        storeData(user.getEmail(), Keys.EMAIL);
        storeData(user.getId(), Keys.ID);
        storeData(user.getPhoneNumber(), Keys.PHONENUMBER);
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
    }
}
