package org.tsofen.mentorim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.tsofen.mentorim.R;
import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.User;

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

        if(currentMode == LayoutsMode.PROFILE_VIEW){
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

            DataManager manager = DataManager.getInstance(this);

            int id = manager.getUser().getId();
            String token = manager.getToken();

            APIManager.getInstance().getUserProfile(id, token, (response, user, exception) -> {
                //update fields
                String fullName = user.getFirstName() + " " + user.getLastName();
                this.fullName.setText(fullName);
                this.emailAddress.setText(user.getEmail());
                phoneNumber.setText(user.getPhoneNumber());

            });
        }
        if(currentMode == LayoutsMode.PROFILE_FILL){
            setContentView(R.layout.activity_profile_fill);
        }
    }

    public static class LayoutsMode {
        public static final int PROFILE_VIEW=1;
        public static final int PROFILE_FILL=0;
    }
}
