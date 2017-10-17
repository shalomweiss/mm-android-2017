package org.tsofen.mentorim;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.tsofen.model.DataManager;
import org.tsofen.model.classes.User;

/**
 * Created by minitour on 17/10/2017.
 */

public class UIViewController extends AppCompatActivity{


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme(DataManager.getInstance(this).getUser());
        enableBackButton();

    }

    protected void enableBackButton(){
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch(NullPointerException ignored){}
    }

    protected void applyTheme(User user){

        if(user != null){
            if(user.isMentor()){
                setTheme(R.style.MentorTheme);
            }else{
                setTheme(R.style.MenteeTheme);
            }
        }
    }
}
