package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.Meeting;
import org.tsofen.model.classes.User;

import java.io.Serializable;
import java.util.ArrayList;

public class MeetingCreateActivity extends UIViewController {


    Spinner spinAssociatedUsers;
    Spinner spinMeetingType;
    SingleDateAndTimePicker picker;
    EditText editLocation;
    EditText editMeetingSubjects;
    EditText editNotes;
    Button btnDone;

    DataManager manager;

    public static final int REQUEST_CODE = 1711;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_meetingcreate);

        init();

        setupAssociatedUsersSpinner();
        setupDatePicker();
        setupButton();
    }

    @Override
    protected void applyTheme(User user) {
        if(user != null){
            if(user.isMentor()){
                setTheme(R.style.MentorAppDialogTheme);
            }else{
                setTheme(R.style.MenteeAppDialogTheme);
            }
        }
    }

    void init(){
        btnDone = (Button) findViewById(R.id.btnDone);
        picker =(SingleDateAndTimePicker) findViewById(R.id.datePicker);
        spinAssociatedUsers =(Spinner) findViewById(R.id.spinAssociatedUsers);
        spinMeetingType = (Spinner) findViewById(R.id.spinMeetingType);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editMeetingSubjects = (EditText) findViewById(R.id.editMeetingSubjects);
        editNotes = (EditText) findViewById(R.id.editNotes);
        manager = DataManager.getInstance(this);
    }

    void setupAssociatedUsersSpinner(){
        User user = manager.getUser();
        ArrayList<String> users = new ArrayList<>();
        User [] associated = manager.getAssociatedUsers();
        for (int i = 0; i <associated.length ; i++) {
            users.add(associated[i].getFirstName() + " " + associated[i].getLastName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,users);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAssociatedUsers.setAdapter(dataAdapter);
    }

    void setupDatePicker(){
        picker.setVisibleItemCount(5);
        picker.setCurved(true);
        picker.setCyclic(true);
        picker.setTextSize(22);
    }

    void setupButton(){
        User current = manager.getUser();
        String token = manager.getToken();
        btnDone.setOnClickListener(v -> {
            Meeting o = createMeetingFromInput();
            APIManager.getInstance().addMeeting(current.getId(), token, o, (response, exception) -> {
                if (response.isOK() ){
                    setResult(RESULT_OK);
                }else{
                    setResult(RESULT_CANCELED);
                }
                runOnUiThread(this::finish);
            });
        });
    }

    int getSelectedUserId(){
        return manager.getAssociatedUsers()[spinAssociatedUsers.getSelectedItemPosition()].getId();
    }


    long getStartingDate(){
        return picker.getDate().getTime();
    }

    long getEndingDate(){
        return picker.getDate().getTime() + 1000L * 60L * 60L;
    }

    String getMeetingType(){
        return ((String)spinMeetingType.getSelectedItem()).toUpperCase().replace(" ","_");
    }

    Meeting createMeetingFromInput(){
        Meeting meeting = new Meeting();
        meeting.setMenteeId(getSelectedUserId());
        meeting.setMeetingType(getMeetingType());
        meeting.setStartingDate(getStartingDate());
        meeting.setEndingDate(getEndingDate());
        meeting.setLocation(editLocation.getText().toString());
        meeting.setSubject(editMeetingSubjects.getText().toString());
        meeting.setNote(editNotes.getText().toString());

        return meeting;
    }

}
