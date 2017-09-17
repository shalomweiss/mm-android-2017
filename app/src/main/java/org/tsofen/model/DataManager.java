package org.tsofen.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by minitour on 17/09/2017.
 */

public final class DataManager {


    private static DataManager instance;
    public static DataManager getInstance(Context context){

        if (instance == null)
            instance = new DataManager(context);
        else
            instance.setContext(context);


        return new DataManager(context);
    }

    private SharedPreferences sharedpreferences;
    private Context context;

    private DataManager(Context context){
        setContext(context);
        setSharedPreferences(context);
    }

    public String getToken(){
        return sharedpreferences.getString(Keys.TOKEN,null);
    }

    public void setToken(String token){
        storeData(token,Keys.TOKEN);
    }

    private Context getContext() {
        return context;
    }

    private void storeData(Object o,String key){
        SharedPreferences.Editor editor = sharedpreferences.edit();

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

        editor.apply();
    }

    public void setContext(Context context) {
        this.context = context;
        setSharedPreferences(context);
    }

    private void setSharedPreferences(Context context) {
        sharedpreferences = context.getSharedPreferences(Keys.PREFERENCES, Context.MODE_PRIVATE);
    }

    private static class Keys {
        private static final String PREFERENCES = "MM_PREFERENCES";

        private static final String TOKEN = "TOKEN";
    }
}
