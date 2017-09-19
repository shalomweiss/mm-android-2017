package org.tsofen.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.tsofen.mentorim.R;

public class ProfileController extends AppCompatActivity {
    private TextView fullName;
    private TextView emailAddress;
    private TextView phoneNumber;
    private TextView major;
    private TextView semesters;
    private TextView university;
    private TextView status;
    private TextView gender;
    private TextView address;
    private TextView joinedDate;
    private TextView summary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        int currentMode=i.getIntExtra("Mode",0);
        if(currentMode== Constants.LayoutsMode.PROFILE_VIEW){
            setContentView(R.layout.activity_profile);
            fullName=(TextView)findViewById(R.id.tvFullName);
            emailAddress=(TextView)findViewById(R.id.tvemailaddress);
            phoneNumber=(TextView)findViewById(R.id.tvPhoneNumber);
            major=(TextView)findViewById(R.id.tvMajor);
            semesters=(TextView)findViewById(R.id.tvSemesters);
            university=(TextView)findViewById(R.id.tvUniversity);
            status=(TextView)findViewById(R.id.tvStatus);
            gender=(TextView)findViewById(R.id.tvGender);
            address=(TextView)findViewById(R.id.tvAddress);
            joinedDate=(TextView)findViewById(R.id.tvJoinedDate);
            summary=(TextView)findViewById(R.id.tvSummary);
        }
        if(currentMode== Constants.LayoutsMode.PROFILE_FILL){
            setContentView(R.layout.activity_profile_fill);
        }
    }
}
