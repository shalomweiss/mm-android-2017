package org.tsofen.mentorim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.classes.User;

public class ProfileController extends AppCompatActivity {


    /**
     * VIEW
     */
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

    /**
     * FILL
     */
    private TextView fillFirstName;
    private TextView fillLastName;
    private TextView fillEmail;
    private TextView fillPhoneNumber;

    //TODO: add the reset of the views



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check input mode
        Intent i=getIntent();
        int currentMode=i.getIntExtra("Mode",1);

        //get data manager
        final DataManager manager = DataManager.getInstance(this);
        final User user = manager.getUser();

        //get user details
        boolean isMentor = user.isMentor();
        int id = user.getId();
        String token = manager.getToken();

        //layout subviews
        if(currentMode== LayoutsMode.PROFILE_VIEW){
            setLayoutView(isMentor);
            loadData(id,token);
        }

        if(currentMode == LayoutsMode.PROFILE_FILL){
            setLayoutFill(isMentor,user);

        }
    }

    /**
     * this method retrun the user with the updated data .
     * @return the user with the updated details
     */

    /**
     * This Method Update The Layout View Of THis Activity
     * @param view The View Layout To Update .
     */
    private void updateLayout(int view){
        setContentView(view);
    }

    private void setLayoutFill(boolean isMentor,User user){
        //TODO: complete this method in the same format as @see{setLayoutView}
        updateLayout(R.layout.activity_profile_fill);
        fillFirstName = (TextView) findViewById(R.id.etFullName);
        fillLastName = (TextView) findViewById(R.id.etLastName);
        fillEmail = (TextView) findViewById(R.id.etemailaddress);
        fillPhoneNumber = (TextView) findViewById(R.id.etPhoneNumber);

        fillFirstName.setText(user.getFirstName());
        fillLastName.setText(user.getLastName());
        fillEmail.setText(user.getEmail());
        fillPhoneNumber.setText(user.getPhoneNumber());
    }

    private void setLayoutView(boolean isMentor){
        updateLayout(R.layout.activity_profile);
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

        if(isMentor){
            findViewById(R.id.education).setVisibility(View.GONE);

        }else{
            findViewById(R.id.occupation).setVisibility(View.GONE);
        }
    }

    private void loadData(int id,String token){
        APIManager.getInstance().getUserProfile(id,token, (response, user, exception) -> {
            //update fields
            runOnUiThread(()->{
                String fullName = user.getFirstName() + " " + user.getLastName();
                this.fullName.setText(fullName);
                this.emailAddress.setText(user.getEmail());
                this.phoneNumber.setText(user.getPhoneNumber());
                this.gender.setText(user.getGender());
                this.address.setText(user.getAddress());
                this.summary.setText(user.getSummary());
                this.status.setText(user.getType());
                this.joinedDate.setText(user.convetLongToDate(user.getJoinedDate()));
                this.major.setText(user.getAcademicDicipline());
                this.secondMajor.setText(user.getAcademicDicipline2());
                this.semesters.setText(user.getRemainingSemesters());
                this.university.setText(user.getAcademiclnstitution());
                this.graduationStatus.setText(user.getGraduationStatus());
                this.average.setText(user.getAverage());
                this.experience.setText(user.getExp());
                this.role.setText(user.getRole());
                this.company.setText(user.getCompany());
                this.volunteering.setText(user.getVolunteering());
                this.workHistory.setText(user.getWorkHistory());
            });

        });
    }

    private void updateData(int id, String token, User user, Callbacks.UpdateProfile callback){
        APIManager.getInstance().updateUserProfile(id,token,user,callback);
    }




    /**
     * This Class Contain A Layouts Finals To Using In Updating View .
     */
    public static class LayoutsMode {
        public static final int PROFILE_VIEW=1;
        public static final int PROFILE_FILL=0;
    }
}
