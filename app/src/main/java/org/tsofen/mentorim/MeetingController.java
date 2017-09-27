package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.crofis.ui.dialog.LoadingDialog;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.User;

public class MeetingController extends AppCompatActivity {
    private TextView tvWithMentee;
    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvAt;
    private TextView tvSubject;
    private TextView tvNotes;
    private EditText etSummery;
    private EditText etPrivateSummary;
    private Button btnConfirm;
    private Button btnDiscard;
    private Button btnApprove;
    private Button btnDecline;
    private APIManager apiManager;
    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingdetails);
        tvWithMentee=(TextView)findViewById(R.id.tvWithMentee);
        tvFrom=(TextView)findViewById(R.id.tvFrom);
        tvAt=(TextView)findViewById(R.id.tvAt);
        tvTo=(TextView)findViewById(R.id.tvTo);
        tvSubject=(TextView)findViewById(R.id.tvSubject);
        tvNotes=(TextView)findViewById(R.id.tvNotes);
        etSummery=(EditText) findViewById(R.id.etSummery);
        etPrivateSummary=(EditText) findViewById(R.id.etPrivateSummery);
        btnConfirm=(Button)findViewById(R.id.btnConfirm);
        btnApprove=(Button)findViewById(R.id.btnApprove);
        btnDiscard=(Button)findViewById(R.id.btnDiscard);
        btnDecline=(Button)findViewById(R.id.btnDecline);
        apiManager=APIManager.getInstance();
        dataManager=DataManager.getInstance(getApplicationContext());
//        btnConfirm.setOnClickListener(view -> {
//            String id=dataManager.getUser().getId()+"";
//            String token=dataManager.getToken();
//            apiManager.confirmMeeting(id,token,);
//        });

//        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
//        dialog.show();
//
//
//        APIManager.getInstance().login("", "", "", (response, user, token, exception) -> {
//        });

    }
}
