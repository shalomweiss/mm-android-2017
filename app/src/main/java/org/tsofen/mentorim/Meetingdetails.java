package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Meetingdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingdetails);
        getSupportActionBar().setTitle("Meeting Details");
        findViewById(R.id.LinearLayoutsecond).setVisibility(View.INVISIBLE);

    }
    public void onapprove(View v){
        findViewById(R.id.LinearLayoutsecond).setVisibility(View.VISIBLE);
        findViewById(R.id.Linearlayoutfirst).setVisibility(View.INVISIBLE);


    }



}

