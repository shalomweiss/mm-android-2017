package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import net.crofis.ui.dialog.BaseAlertDialog;
import net.crofis.ui.dialog.CustomViewDialog;

public class MeetingCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingcreate);

        CustomViewDialog dialog = new CustomViewDialog(this);

        //For this example I will added a CalenderView.
        CalendarView calendarView = new CalendarView(this);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            }
        });

        //Setup Button listener.
        dialog.setPositiveListener((v, dialog1) -> {
            dialog1.dismiss();
        });

        //Set your custom view.
        dialog.setCustomView(calendarView);

        //show dialog.
        dialog.show();
    }
}
