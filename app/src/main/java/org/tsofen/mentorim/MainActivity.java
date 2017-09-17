package org.tsofen.mentorim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.classes.User;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Use-case example:
    void login(){
        APIManager.getInstance().login("demo@email.com", "password123", (user, token, ex) -> {
            if (ex == null){
                //we got user and token

            }else
                ex.printStackTrace();
        });
    }
}
