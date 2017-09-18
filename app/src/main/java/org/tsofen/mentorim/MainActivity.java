package org.tsofen.mentorim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.classes.User;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create data manager instance
        final DataManager manager = DataManager.getInstance(this);

        //fetch token
        String token = manager.getToken();

        //check if token exists
        if (token == null)
            goToLogin();
        else
            loadData();
    }

    private void loadData(){
        //TODO: Load meetings from server.
    }

    private void goToLogin(){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

}
