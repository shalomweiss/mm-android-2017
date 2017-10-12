package org.tsofen.mentorim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import net.crofis.ui.dialog.LoadingDialog;

import org.tsofen.model.APIManager;
import org.tsofen.model.DataManager;
import org.tsofen.model.classes.User;

public class ProfileController extends AppCompatActivity {
    private TextView fullName;
    private TextView emailAddress;
    private TextView phoneNumber;
    private TextView gender;
    private TextView address;
    private TextView summary;
    private TextView status;
    private TextView joinedDate;
    private TextView major;
    private TextView secondMajor;
    private TextView semesters;
    private TextView university;
    private TextView graduationStatus;
    private TextView average;
    private TextView experience;
    private TextView role;
    private TextView company;
    private TextView volunteering;
    private TextView workHistory;

    private TextView fillFirstName;
    private TextView fillLastName;
    private TextView fillEmail;
    private TextView fillPhoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        int currentMode=i.getIntExtra("Mode",0);
        DataManager manager = DataManager.getInstance(this);
        int id = manager.getUser().getId();
        String token = manager.getToken();
        // Updated upstream:app/src/main/java/org/tsofen/mentorim/ProfileController.java
        if(currentMode== LayoutsMode.PROFILE_VIEW){
        // Stashed changes:app/src/main/java/org/tsofen/model/ProfileController.java
            updateProfile(R.layout.activity_profile);
            fullName=(TextView)findViewById(R.id.tvFullName);
            emailAddress=(TextView)findViewById(R.id.tvemailaddress);
            phoneNumber=(TextView)findViewById(R.id.tvPhoneNumber);
            gender=(TextView)findViewById(R.id.tvGender);
            address=(TextView)findViewById(R.id.tvAddress);
            summary=(TextView)findViewById(R.id.tvSummary);
            status=(TextView)findViewById(R.id.tvStatus);
            joinedDate=(TextView)findViewById(R.id.tvJoinedDate);
            major=(TextView)findViewById(R.id.tvMajor);
            secondMajor=(TextView)findViewById(R.id.tvSecondMajor);
            semesters=(TextView)findViewById(R.id.tvSemesters);
            university=(TextView)findViewById(R.id.tvUniversity);
            graduationStatus=(TextView)findViewById(R.id.tvGraduationStatus);
            average=(TextView)findViewById(R.id.tvAverage);
            experience=(TextView)findViewById(R.id.tvExperience);
            role=(TextView)findViewById(R.id.tvRole);
            company=(TextView)findViewById(R.id.tvCompany);
            volunteering=(TextView)findViewById(R.id.tvVolunteering);
            workHistory=(TextView)findViewById(R.id.tvWorkHistory);

            APIManager.getInstance().getUserProfile(id, token, (response, user, exception) -> {
                //update fields
                String fullName = user.getFirstName() + " " + user.getLastName();
                this.fullName.setText(fullName);
                this.emailAddress.setText(user.getEmail());
                this.phoneNumber.setText(user.getPhoneNumber());
                this.gender.setText(user.getGender());
                this.address.setText(user.getAddress());
                this.summary.setText(user.getSummary());
                this.status.setText(user.getType());
                this.joinedDate.setText(user.convetLongToDate(user.getJoinedDate()));
                this.major.setText(user.getMajor());
                this.secondMajor.setText(user.getSecondMajor());
                this.semesters.setText(user.getSemesters());
                this.university.setText(user.getUniversity());
                this.graduationStatus.setText(user.getGraduationStatus());
                this.average.setText(user.getAverage());
                this.experience.setText(user.getExp());
                this.role.setText(user.getRole());
                this.company.setText(user.getCompany());
                this.volunteering.setText(user.getVolunteering());
                this.workHistory.setText(user.getWorkHistory());

            });
        }
        // Updated upstream:app/src/main/java/org/tsofen/mentorim/ProfileController.java
        if(currentMode == LayoutsMode.PROFILE_FILL){
            updateProfile(R.layout.activity_profile_fill);
            fillFirstName = (TextView) findViewById(R.id.etFullName);
            fillLastName = (TextView) findViewById(R.id.etLastName);
            fillEmail = (TextView) findViewById(R.id.etemailaddress);
            fillPhoneNumber = (TextView) findViewById(R.id.etPhoneNumber);

            fillFirstName.setText(manager.getUser().getFirstName());
            fillLastName.setText(manager.getUser().getLastName());
            fillEmail.setText(manager.getUser().getEmail());
            fillPhoneNumber.setText(manager.getUser().getPhoneNumber());
           //Write this code in the Done / Update On Click Listener
            User u=updateProfile();
            APIManager.getInstance().updateUserProfile(id, token, u, (response, user, ex) -> {
                if(ex==null){
                    if(response.isOK()){
                        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
                        dialog.show();
                        dialog.complete(false,true,"Update User Profile","Updating success !");
                    }else{
                        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
                        dialog.show();
                        dialog.complete(false,false,"Update User Profile","Updating Failed, Response Error : "+response.getMessage());
                        Log.e("Server Error","Updating Failed, Response Error : "+response.getMessage());
                    }
                }else{
                    final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
                    dialog.show();
                    dialog.complete(false,false,"Update User Profile","Updating Failed, Server Error : " + ex.getMessage());
                    Log.e("Server Error","Updating Failed, Response Error : "+ex.getMessage());
                }
                //End Of  code in the Done / Update On Click Listener
            });

        }
    }

    /**
     * this method retrun the user with the updated data .
     * @return the user with the updated details
     */
    User updateProfile() {
        User u = new User();
        int firstSpace=fullName.getText().toString().indexOf(" ");
        u.setFirstName(fullName.getText().toString().substring(0,firstSpace));
        u.setLastName(fullName.getText().toString().substring(firstSpace+1));
        u.setEmail(emailAddress.getText().toString());
        u.setType(status.getText().toString());
        u.setUniversity(university.getText().toString());
        u.setSemesters(semesters.getText().toString());
        u.setSummary(summary.getText().toString());
        u.setAddress(address.getText().toString());
        u.setGender(gender.getText().toString());
        u.setMajor(major.getText().toString());
        u.setPhoneNumber(phoneNumber.getText().toString());
        u.setJoinDate(Long.parseLong(joinedDate.getText().toString()));
        return u;
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
