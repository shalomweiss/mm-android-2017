package org.tsofen.mentorim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.tsofen.model.APIManager;
import org.tsofen.model.DataManager;

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
        // Updated upstream:app/src/main/java/org/tsofen/mentorim/ProfileController.java
        if(currentMode== LayoutsMode.PROFILE_VIEW){
        // Stashed changes:app/src/main/java/org/tsofen/model/ProfileController.java
            updateProfile(R.layout.activity_profile);
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
                this.phoneNumber.setText(user.getPhoneNumber());
                this.major.setText(user.getMajor());
                this.semesters.setText(user.getSemesters());
                this.university.setText(user.getUniversity());
                this.status.setText(user.getStatus());
                this.gender.setText(user.getGender());
                this.address.setText(user.getAddress());
                this.summary.setText(user.getSummary());
                this.joinedDate.setText(user.convetLongToDate(user.getJoinedDate()));
            });
        }
        // Updated upstream:app/src/main/java/org/tsofen/mentorim/ProfileController.java
        if(currentMode == LayoutsMode.PROFILE_FILL){
            updateProfile(R.layout.activity_profile_fill);
        }
    }

    /**
     * This Method Update The Layout View Of THis Activity
     * @param view The View Layout To Update .
     */
    public void updateProfile(int view){
        setContentView(view);
    }
    // Stashed changes:app/src/main/java/org/tsofen/model/ProfileController.java

    /**
     * This Class Contain A Layouts Finals To Using In Updating View .
     */
    public static class LayoutsMode {
        public static final int PROFILE_VIEW=1;
        public static final int PROFILE_FILL=0;
    }
}
