package org.tsofen.mentorim;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.crofis.ui.dialog.BaseAlertDialog;
import net.crofis.ui.dialog.DialogManager;
import net.crofis.ui.dialog.InfoDialog;
import net.crofis.ui.dialog.LoadingDialog;
import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.Meeting;
public class MeetingController extends AppCompatActivity {
    private TextView tvWithMentee;
    private TextView tvMeetingType;
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
    private int meetingId;
    private Meeting meeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingdetails);
        meetingId = getIntent().getIntExtra(Constants.MEETING_ID,-1);
        tvWithMentee=(TextView)findViewById(R.id.tvWithMentee);
        tvMeetingType=(TextView)findViewById(R.id.tvMeetingType);
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
        dataManager=DataManager.getInstance(this);
        btnConfirm.setOnClickListener(view -> {
            doConfirm();
        });
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doApprove();
            }
        });
        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDecline();
            }
        });
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDiscard();
            }
        });
        if(meetingId != -1){
            int userId = dataManager.getUser().getId();
            String token = dataManager.getToken();
            apiManager.getMeetingByID(userId, token, meetingId, (response, meeting1, exception) -> {
                if(exception == null) {
                    this.meeting = meeting1;
                    //load meeting to views
                    loadMeeting(meeting1);
                }else{
                    //show error
                    meetingErrorOcuuredDialog();
                }
            });
        }else{
            meetingNotExistDialog();
//            btnDiscard.setEnabled(false);
//            btnDecline.setEnabled(false);
//            btnApprove.setEnabled(false);
//            btnConfirm.setEnabled(false);
        }
    }
    private void  doDiscard(){
        int id=dataManager.getUser().getId();
        String token=dataManager.getToken();
        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
        dialog.show();
        apiManager.confirmMeeting(id, token, meetingId+"", false, (response, exception) -> {
            if(exception==null){
                if(response.isOK()){
                    dialog.complete(false,true,"Discarding","The Meeting Is Discarded !");
                }else{
                    dialog.complete(false,false,"Discarding","The Meeting Wasn't Discarded !");
                }
            }else{
                dialog.complete(false,false,"Discarding","Error Occurred, The Meeting Wasn't Discarded !");
            }
        });
    }
    private void  doDecline(){
        int id=dataManager.getUser().getId();
        String token=dataManager.getToken();
        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
        dialog.show();
        apiManager.approveMeeting(id, token, meetingId + "", false, new Callbacks.General() {
            @Override
            public void make(ServerResponse response, Exception exception) {
                if(exception==null){
                    if(response.isOK()) {
                        dialog.complete(false,true,"Declining","The Meeting Is Declined !");
                    }else{
                        dialog.complete(false,false,"Declining","The Meeting Wasn't Declined !");
                    }
                }else{
                    dialog.complete(false,false,"Declining","Error Occurred, The Meeting Wasn't Declined !");
                }
            }
        });
    }
    private void  doApprove(){
        int id=dataManager.getUser().getId();
        String token=dataManager.getToken();
        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
        dialog.show();
        apiManager.approveMeeting(id, token, meetingId + "", true, new Callbacks.General() {
            @Override
            public void make(ServerResponse response, Exception exception) {
                if(exception==null){
                    if(response.isOK()) {
                        dialog.complete(false,true,"Approving","The Meeting Is Approved !");
                    }else{
                        dialog.complete(false,false,"Approving","The Meeting Wasn't Approved !");
                    }
                }else{
                    dialog.complete(false,false,"Approving","Error Occurred, The Meeting Wasn't Approved !");
                }
            }
        });
    }
    private void doConfirm(){
        int id=dataManager.getUser().getId();
        String token=dataManager.getToken();
        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
        dialog.show();
        apiManager.confirmMeeting(id, token, meetingId+"", true, (response, exception) -> {
            if(exception==null){
                if(response.isOK()){
                    dialog.complete(false,true,"Confirming","The Meeting Is Confirmed !");
                }else{
                    dialog.complete(false,false,"Confirming","The Meeting Wasn't Confirmed !");
                }
            }else{
                dialog.complete(false,false,"Confirming","Error Occurred, The Meeting Wasn't Confirmed !");
            }
        });
    }
    private void loadMeeting(Meeting meeting){
        tvWithMentee.setText(meeting.getWithMentee());
        tvMeetingType.setText(meeting.getType());
        tvFrom.setText(meeting.getFrom().getTime()+"");
        tvTo.setText(meeting.getTo().getTime()+"");
        tvAt.setText(meeting.getAt());
        tvNotes.setText(meeting.getNote());
        tvSubject.setText(meeting.getSubject());
    }
    private static final class Constants{
        public static String MEETING_ID = "MEETING_ID";
    }
    private void meetingNotExistDialog(){
        InfoDialog infoDialog = new InfoDialog(this);
        infoDialog.setTitle("Meeting Action");
        infoDialog.setMessage("This Meeting Id : "+meetingId+" is not Exist in The System!");
        infoDialog.setPostiveButtonOnClickListener(new BaseAlertDialog.OnClickListener() {
            @Override
            public void onClick(View v, BaseAlertDialog dialog) {
                //Do whatever
                //You must add this otherwise the dialog will not dismiss.
                dialog.dismiss();
            }
        });

        infoDialog. setNegativeButtonOnClickListener(new BaseAlertDialog.OnClickListener() {
            @Override
            public void onClick(View v, BaseAlertDialog dialog) {
                //Do whatever
                dialog.dismiss();
            }
        });

        infoDialog.show();
    }
    private void meetingErrorOcuuredDialog(){
        DialogManager
                .makeDialog(this,"Meeting Action","some Error Occurred When Getting The Meeting With This Meeting Id : "+meetingId+" .")
                .show();
    }
}
