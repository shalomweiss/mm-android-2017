package org.tsofen.mentorim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.crofis.ui.dialog.LoadingDialog;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.User;

public class ProfileController extends UIViewController {


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
    //ContactDetails
    private TextView fillFirstName;
    private TextView fillLastName;
    private TextView fillEmail;
//  private TextView fillPhoneNumber;
    private EditText fillPhoneNumber;
    // Education Fields
    private EditText fillEtMajor;
    private EditText fillEtSecondMajor;
    private EditText fillEtGraduationStatus;
    private EditText fillEtAverage;
    private EditText fillEtSemesters;
    private EditText fillEtUniversity;
    // Info Fields
    private EditText fillEtStatus;
    private EditText fillEtGender;
    private EditText fillEtAddress;
    private EditText fillEtJoinedDate;
    private EditText fillEtSummary;
    //Occupation Fields
    private EditText fillEtExperience;
    private EditText fillEtRole;
    private EditText fillEtCompany;
    private EditText fillEtVolunteering;
    private EditText fillEtWorkHistory;
    private boolean showSelf;
    //TODO: add the reset of the views



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //check input mode
        Intent i=getIntent();

        User optional = (User) i.getSerializableExtra(LayoutsMode.VIEW_OTHER);

        showSelf = true;

        if(optional != null){
            showSelf = false;
        }

        int currentMode= showSelf ? i.getIntExtra("Mode",1) : LayoutsMode.PROFILE_VIEW;

        //get data manager
        final DataManager manager = DataManager.getInstance(this);
        final User user = showSelf ? manager.getUser() : optional;

        //get user details
        boolean isMentor = user.isMentor();
        int id = user.getId();
        String token = manager.getToken();

        //layout subviews
        if(currentMode== LayoutsMode.PROFILE_VIEW){
            setLayoutView(isMentor);
            loadData(id,token,!showSelf);
        }

        if(currentMode == LayoutsMode.PROFILE_FILL){
            setLayoutFill(isMentor,user);
            loadData_Fill(id,token);
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
        //ContactDetails
        fillFirstName = (TextView) findViewById(R.id.etFullName);
        fillLastName = (TextView) findViewById(R.id.etLastName);
        fillEmail = (TextView) findViewById(R.id.etemailaddress);
        fillPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        // Education Fields
        fillEtMajor=(EditText) findViewById(R.id.etMajor);
        fillEtSecondMajor=(EditText)findViewById(R.id.fillEtSecondMajor);
        fillEtSemesters=(EditText) findViewById(R.id.etSemesters);
        fillEtUniversity=(EditText) findViewById(R.id.etUniversity);
        fillEtGraduationStatus=(EditText)findViewById(R.id.fillEtGraduationStatus);
        fillEtAverage=(EditText)findViewById(R.id.fillEtAverage);
        // Info Fields
        fillEtStatus=(EditText) findViewById(R.id.etStatus);
        fillEtGender=(EditText) findViewById(R.id.etGender);
        fillEtAddress=(EditText) findViewById(R.id.etAddress);
        fillEtJoinedDate=(EditText) findViewById(R.id.etJoinedDate);
        fillEtSummary=(EditText) findViewById(R.id.etSummary);
        //Occupation Fields
        fillEtExperience=(EditText) findViewById(R.id.fillEtExperience);
        fillEtRole=(EditText) findViewById(R.id.fillEtRole);
        fillEtCompany=(EditText) findViewById(R.id.fillEtCompany);
        fillEtVolunteering=(EditText) findViewById(R.id.fillEtVolunteering);
        fillEtWorkHistory=(EditText) findViewById(R.id.fillEtWorkHistory);
        if(isMentor){
            findViewById(R.id.fillEducation_Layouts).setVisibility(View.GONE);
        }else{
            findViewById(R.id.fill_occupation_layouts).setVisibility(View.GONE);
        }
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

    private void loadData(int id,String token,boolean loadOther){
        if (loadOther){
           User[] users = DataManager.getInstance(this).getAssociatedUsers();
            for (User u : users){
                if(u.getId() == id){
                    applyData(u);
                    break;
                }
            }

        }else APIManager.getInstance().getUserProfile(id,token, (response, user, exception) -> {
            //update fields
            ProfileController.this.runOnUiThread(() -> applyData(user));

        });
    }


    private void applyData(User user){
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
    }

    private void applyData_Fill(User user){
        //Set Fields Data
        fillFirstName.setText(user.getFirstName());
        fillLastName.setText(user.getLastName());
        fillEmail.setText(user.getEmail());
        fillPhoneNumber.setText(user.getPhoneNumber());
        fillEtMajor.setText(user.getAcademicDicipline());
        fillEtSecondMajor.setText(user.getAcademicDicipline2());
        fillEtSemesters.setText(user.getRemainingSemesters());
        fillEtUniversity.setText(user.getAcademiclnstitution());
        fillEtGraduationStatus.setText(user.getGraduationStatus());
        fillEtAverage.setText(user.getAverage());
        fillEtStatus.setText(user.getGraduationStatus());
        fillEtGender.setText(user.getGender());
        fillEtAddress.setText(user.getAddress());
        fillEtJoinedDate.setText(user.getJoinedDate()+"");
        fillEtSummary.setText(user.getSummary());
        //Occupation Fields
        fillEtExperience.setText(user.getExp());
        fillEtRole.setText(user.getRole());
        fillEtCompany.setText(user.getCompany());
        fillEtVolunteering.setText(user.getVolunteering());
        fillEtWorkHistory.setText(user.getWorkHistory());
    }

    private void loadData_Fill(int id,String token){
        APIManager.getInstance().getUserProfile(id, token, (response, user, exception) -> {
            ProfileController.this.runOnUiThread(() -> applyData_Fill(user));
        });
    }

    private void updateData(int id, String token, User user, Callbacks.UpdateProfile callback){
        APIManager.getInstance().updateUserProfile(id,token,user,callback);
    }


    Menu currentMenu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!showSelf)
            return false;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        menu.getItem(0).setVisible(false);
        currentMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //get data manager
        final DataManager manager = DataManager.getInstance(this);
        final User user = manager.getUser();
        //get user details
        boolean isMentor = user.isMentor();
        int userId = user.getId();
        String token = manager.getToken();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile_save) {
            User newUser=updatUserData();
            final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
            dialog.show();
            updateData(userId, token, newUser, (response, user1, ex) -> {
                runOnUiThread(()->{
                    if(ex==null){
                        if(response.isOK()){
                            dialog.complete(false,true,"Saving Profile Data","The Profile Data Is Saved !");
                        }else{
                            dialog.complete(false,false,"Saving Profile Data","The Profile Data Wasn't Saved !");
                        }
                    }else{
                        dialog.complete(false,false,"Saving Profile Data","Error Occurred, The Profile Data Wasn't Saved !");
                    }
                });
            });
            currentMenu.getItem(0).setVisible(false);
            currentMenu.getItem(1).setVisible(true);
            setLayoutView(isMentor);
            loadData(userId,token,false);
            return true;
        }

        if (id == R.id.action_profile_edit) {
            currentMenu.getItem(0).setVisible(true);
            currentMenu.getItem(1).setVisible(false);
            setLayoutFill(isMentor,user);
            loadData_Fill(userId,token);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    private User updatUserData(){
        User updateUser=DataManager.getInstance(this).getUser() ;
        updateUser.setFirstName(fillFirstName.getText().toString());
        updateUser.setLastName(fillLastName.getText().toString());
        updateUser.setEmail(fillEmail.getText().toString());
        updateUser.setPhoneNumber(fillPhoneNumber.getText().toString());
        updateUser.setGender(fillEtGender.getText().toString());
        updateUser.setAddress(fillEtAddress.getText().toString());
        updateUser.setSummary(fillEtSummary.getText().toString());
        updateUser.setType(fillEtStatus.getText().toString());
        updateUser.setJoinDate(Long.parseLong(fillEtJoinedDate.getText().toString()));
        updateUser.setAcademicDicipline(fillEtMajor.getText().toString());
        updateUser.setAcademicDicipline2(fillEtSecondMajor.getText().toString());
        updateUser.setAcademiclnstitution(fillEtUniversity.getText().toString());
        updateUser.setRemainingSemesters(fillEtSemesters.getText().toString());
        updateUser.setGraduationStatus(fillEtGraduationStatus.getText().toString());
        updateUser.setAverage(fillEtAverage.getText().toString());
        updateUser.setExp(fillEtExperience.getText().toString());
        updateUser.setRole(fillEtRole.getText().toString());
        updateUser.setCompany(fillEtCompany.getText().toString());
        updateUser.setVolunteering(fillEtVolunteering.getText().toString());
        updateUser.setWorkHistory(fillEtWorkHistory.getText().toString());
        return updateUser;
    }
    /**
     * This Class Contain A Layouts Finals To Using In Updating View .
     */
    public static class LayoutsMode {
        public static final int PROFILE_VIEW=1;
        public static final int PROFILE_FILL=0;
        public static final String VIEW_OTHER = "VIEW_OTHER";
    }
}
