package org.tsofen.mentorim;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

import net.crofis.ui.dialog.BaseAlertDialog;
import net.crofis.ui.dialog.DialogManager;
import net.crofis.ui.dialog.InfoDialog;
import net.crofis.ui.dialog.LoadingDialog;
import net.crofis.ui.dialog.NewMessageDialog;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.User;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        //Overriding method to prevent user from closing the activity.
        moveTaskToBack(true);
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

        String deviceId = null;
        try{
            deviceId = FirebaseInstanceId.getInstance().getToken();
            Log.i("APP",deviceId);
        }catch (NullPointerException e){}


        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();




        //Make API Call
        APIManager.getInstance().login(email, password,deviceId, (response, user, token, ex) -> {

            if (ex == null) {
                //

                Log.i("Login", response.getMessage());
                //create data manager instance from context
                DataManager manager = DataManager.getInstance(LoginActivity.this);

                //store token
                manager.setToken(token);

                //store user
                manager.setUser(user);

                APIManager.getInstance().getAssociatedUsers(user.getId(), token, user.isMentor(), (response1, users, ex1) -> {

                    //store users
                    manager.associatedUsers(users);

                    runOnUiThread(()->{
                        pDialog.dismiss();

                        //close activity
                        LoginActivity.this.finish();
                    });
                });


            } else {
                //TODO: Handle exception.
                LoginActivity.this.runOnUiThread(() -> {
                    pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().stopSpinning();
                    pDialog.setTitleText("Error");
                    pDialog.setContentText(ex.getMessage());
                });
            }
        });
    }

}
