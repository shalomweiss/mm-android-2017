package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.tsofen.model.APIManager;
import org.tsofen.model.DataManager;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        //Overriding method to prevent user from closing the activity.
    }

    private EditText edtEmail;
    private EditText edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(getString(R.string.signIn));

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        findViewById(R.id.btnSignIn).setOnClickListener(view -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            login(email,password);
        });
    }

    /**
     * Method used to call the login api.
     * @param email The email to login with.
     * @param password The password of the account.
     */
    private void login(String email,String password){

        //TODO: verify email with regex.
        //TODO: verify password length
        String deviceId = "";

        //Make API Call
        APIManager.getInstance().login(email, password,deviceId, (response, user, token, ex) -> {


            if (ex == null){
                Log.i("Login", response.getMessage());
                //create data manager instance from context
                DataManager manager = DataManager.getInstance(this);

                //store token
                manager.setToken(token);

                //store user
                manager.setUser(user);

                //close activity
                finish();
            }else{
                //TODO: Handle exception.
                ex.printStackTrace();
            }
        });
    }

}
