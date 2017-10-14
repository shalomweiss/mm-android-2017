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
import net.crofis.ui.dialog.NewMessageDialog;

import org.tsofen.model.APIManager;
import org.tsofen.model.Callbacks;
import org.tsofen.model.DataManager;
import org.tsofen.model.ServerResponse;
import org.tsofen.model.classes.Meeting;
import org.tsofen.model.classes.User;

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
    private Button btnCancel;

    private APIManager apiManager;
    private DataManager dataManager;
    private int meetingId;
    private Meeting meeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingdetails);

        meeting = (Meeting) getIntent().getSerializableExtra(Constants.MEETING_ID);
        meetingId = meeting != null ? meeting.getMeetingId() : -1;
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
        btnCancel = (Button) findViewById(R.id.btnCancel);

        apiManager=APIManager.getInstance();
        dataManager=DataManager.getInstance(this);

        btnConfirm.setOnClickListener(view -> doConfirm());
        btnApprove.setOnClickListener(view -> doApprove());
        btnDecline.setOnClickListener(view -> doDecline());
        btnDiscard.setOnClickListener(view -> doDiscard());
        btnCancel.setOnClickListener(view -> doCancel());

        if(meeting != null){
            loadMeeting(meeting,dataManager);
        }else{
            meetingNotExistDialog();
        }
    }

    private void doCancel() {
        User current = dataManager.getUser();
        User[] associated = dataManager.getAssociatedUsers();

        NewMessageDialog messageDialog = DialogManager.makeMessageDialog(this, "Cancel Meeting", true);
        //String header = meeting.getMeetingTitle(current.isMentor(),associated);
        messageDialog.getInputTitle().setVisibility(View.GONE);
        messageDialog.getInputMessage().setMinLines(5);
        messageDialog.getInputMessage().setLines(5);
        messageDialog.getInputMessage().setHint("Why are you canceling the meeting?");
        messageDialog.setPostiveButtonOnClickListener((v, dialog) -> {
            //TODO: send to server
        });
        messageDialog.setNegativeButtonOnClickListener((v,dialog)->{
            dialog.dismiss();
        });
        messageDialog.setAllowCameraButton(false);

        messageDialog.show();

    }

    /**
     * this method do The Discard Code When Check The Discard Button
     */
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
    /**
     * this method do The Decline Code When Check The Decline Button
     */
    private void  doDecline(){
        int id=dataManager.getUser().getId();
        String token=dataManager.getToken();
        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
        dialog.show();
        apiManager.approveMeeting(id, token, meetingId + "", false, (response, exception) -> {
            runOnUiThread(()->{
                if(exception==null){
                    if(response.isOK()) {
                        dialog.complete(false,true,"Declining","The Meeting Is Declined !");
                    }else{
                        dialog.complete(false,false,"Declining","The Meeting Wasn't Declined !");
                    }
                }else{
                    dialog.complete(false,false,"Declining","Error Occurred, The Meeting Wasn't Declined !");
                }
            });
        });
    }
    /**
     * this method do The Approve Code When Check The Approve Button
     */
    private void  doApprove(){
        int id=dataManager.getUser().getId();
        String token=dataManager.getToken();
        final LoadingDialog dialog = new LoadingDialog(this,"Loading...");
        dialog.show();
        apiManager.approveMeeting(id, token, meetingId + "", true, (response, exception) -> {
            runOnUiThread(()->{
                if(exception==null){
                    if(response.isOK()) {
                        dialog.complete(false,true,"Approving","The Meeting Is Approved !");
                    }else{
                        dialog.complete(false,false,"Approving","The Meeting Wasn't Approved !");
                    }
                }else{
                    dialog.complete(false,false,"Approving","Error Occurred, The Meeting Wasn't Approved !");
                }
            });
        });
    }
    /**
     * this method do The Confirm Code When Check The Confirm Button
     */
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
    /**
     *Load The Meeting Details To The TextViews In Layout
     * @param meeting The Meeting Object To Load
     */
    private void loadMeeting(Meeting meeting,DataManager manager){

        User current = manager.getUser();
        User[] associated = manager.getAssociatedUsers();
        //TODO: update
        tvWithMentee.setText(meeting.getMeetingTitle(current.isMentor(),associated));
        tvMeetingType.setText(meeting.getMeetingType());
        tvFrom.setText(meeting.getStartTime());
        //tvTo.setText((""+ meeting.getEndingDate()));
        tvAt.setText(meeting.getLocation());
        tvNotes.setText(meeting.getNote());
        tvSubject.setText(meeting.getSubject());

        boolean isMentor = current.isMentor();
        if(meeting.getStatusInt() == 0){
            //show approve/decline layout
            findViewById(R.id.LinearLayoutsecond).setVisibility(View.GONE);

            if(isMentor){
                findViewById(R.id.Linearlayoutfirst).setVisibility(View.GONE);
            }else{
                btnCancel.setVisibility(View.GONE);
            }

        }

        if(meeting.getStatusInt() == 1){
            //hide both layouts
            findViewById(R.id.Linearlayoutfirst).setVisibility(View.GONE);
            findViewById(R.id.LinearLayoutsecond).setVisibility(View.GONE);
        }

        if(meeting.getStatusInt() == 2){
            //show summary layout
            findViewById(R.id.Linearlayoutfirst).setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
        }
    }
    /**
     *This Class Contain Final keys
     */
    public static final class Constants{
        public static final String MEETING_ID = "MEETING_ID";
    }
    /**
     * This Method Show A Dialog With Error Message When The Meeting Isn't Exist In The System .
     */
    private void meetingNotExistDialog(){
        InfoDialog infoDialog = new InfoDialog(this);
        infoDialog.setTitle("Meeting Action");
        infoDialog.setMessage("This Meeting Id : "+meetingId+" is not Exist in The System!");
        infoDialog.setPostiveButtonOnClickListener((v, dialog) -> {
            //Do whatever
            //You must add this otherwise the dialog will not dismiss.
            dialog.dismiss();
            finish();
        });


        infoDialog.show();
    }
    /**
     * This Method Show A Dialog With Error Message
     */
    private void meetingErrorOcuuredDialog(){
        runOnUiThread(()->{
            DialogManager
                    .makeDialog(this,"Meeting Action","some Error Occurred When Getting The Meeting With This Meeting Id : "+meetingId+" .")
                    .show();
        });

    }
}
