package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

import net.crofis.ui.dialog.BaseAlertDialog;
import net.crofis.ui.dialog.CustomViewDialog;

import org.tsofen.model.DataManager;
import org.tsofen.model.classes.User;

import java.util.ArrayList;
import java.util.Arrays;

public class MeetingCreateActivity extends AppCompatActivity {

    SingleDateAndTimePicker picker;
    Spinner spinAssociatedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingcreate);
        picker =(SingleDateAndTimePicker) findViewById(R.id.datePicker);
        picker.setVisibleItemCount(5);
        picker.setCurved(true);
        picker.setCyclic(true);
        picker.setTextSize(22);

        spinAssociatedUsers =(Spinner) findViewById(R.id.spinAssociatedUsers);

        DataManager manager = DataManager.getInstance(this);

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

        if(!user.isMentor()){
            spinAssociatedUsers.setSelection(0);
        }

    }
}
