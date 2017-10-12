package org.tsofen.model;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.tsofen.model.classes.Meeting;
import org.tsofen.model.classes.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class APIManager {
    /**
     * Get Instance
     * @return
     */
    public static APIManager getInstance(){
        return manager;
    }
    //singleton
    private final static APIManager manager = new APIManager();
    private final OkHttpClient client;
    private final String TAG = "API-MANAGER";
    /**
     * Private constructor
     */
    private APIManager(){
        client = new OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Login function. This method is used when we want to sign in to the application.
     * This will return a session token and a user object.
     *
     * @param email The email of the account.
     * @param password The password of the account.
     * @param deviceId The id of the device.
     * @param callback The callback function.
     */
    public void login(String email,String password,String deviceId,final Callbacks.Auth callback){

        Map<String,Object> params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);
        params.put("deviceId",deviceId);

        makeRequest(Constants.Routes.login(), params, (json, ex) -> {
            if (ex == null) {
                //OK

                ServerResponse response = new ServerResponse(json);

                if (response.isOK()) {
                    //Success
                    //Convert jasonObject To User Object
                    JsonObject jsonUser = json.getAsJsonObject("user");
                    User user = new User().init(jsonUser);
                    //fetch token
                    String token = json.get("token").getAsString();
                    callback.make(response, user, token, null);
                } else {
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response, null, null, e);
                }
            } else {
                callback.make(null, null, null, ex);
            }
        });
    }

    /**
     *
     * @param id The id of the user.
     * @param token The session token.
     * @param callback Callback function.
     */
    public void getUserProfile(int id, String token, final Callbacks.GetProfile callback){
        Map<String,Object> params = new HashMap<>();
        params.put("id", id);
        params.put("token", token);
        makeRequest(Constants.Routes.getProfile(), params, (json, ex) -> {
            if (ex == null) {
                //OK

                ServerResponse response = new ServerResponse(json);

                if(response.isOK()){
                    //Success
                    //Convert jasonObject To User Object
                    JsonObject jsonUser = json.getAsJsonObject("user");
                    User user=new User().init(jsonUser);
                    callback.make(response,user, null);

                }else{
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response,null,e);

                }
            }else{
                callback.make(null,null ,ex);
            }
        });
    }
    /**
     * This Method Get A Meetings Data From The Server
     * @param id The id of the user.
     * @param token The session token.
     * @param meetingStatus The Status Of THe Meetings
     * @param count The Number Of Meetings To Get
     * @param page The Number Of Pages That Contain This Meetings
     * @param callback Callback function.
     */
    public void getMeetings(int id, String token,int meetingStatus,int count,int page,Callbacks.GetMeetings callback){
        Map<String,Object> params=new HashMap<>();
        params.put("id",id);
        params.put("token",token);
        params.put("meetingStatus",meetingStatus);
        params.put("count",count);
        params.put("page",page);
        makeRequest(Constants.Routes.getMeetings(), params, (json, ex) -> {
            if (ex == null) {
                //OK
                ServerResponse response = new ServerResponse(json);
                if (response.isOK()) {   //Success
                    //Convert jasonObject To User Object
                    JsonArray obj = json.getAsJsonArray("meetings");
                    ArrayList<Meeting> meetings = new ArrayList<>();
                    for (JsonElement o : obj) {
                        Meeting m = new Meeting().init(o.getAsJsonObject());
                        meetings.add(m);
                    }
                    callback.make(response,meetings, null);
                } else {
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response,null, e);
                }
            } else {
                callback.make(null,null, ex);
            }
        });
    }
    /**
     * This Method Get A Meeting Data By Id From The Server
     * @param id The id of the user.
     * @param token The session token.
     * @param meetingId THe Id Of The Meeting To Get From The Server
     * @param callback Callback function.
     */
    public void getMeetingByID(int id,String token,int meetingId,Callbacks.GetMeetingByID callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("token", token);
        params.put("meetingId", meetingId);
        makeRequest(Constants.Routes.getMeetingById(), params, (json, ex) -> {
            if (ex == null) {//OK

                ServerResponse response = new ServerResponse(json);

                if (response.isOK()) {

                    //Success
                    //Convert jasonObject To Meeting Object
                    JsonObject meeting = json.getAsJsonObject("meeting");
                    Meeting m = new Meeting().init(meeting);

                    callback.make(response,m, null);

                } else {
                    //Failed
                    //ToDo : handle Code return Specifec Exception

                    ServerException e = new ServerException(response);

                    callback.make(response,null, e);

                }
            } else {

                callback.make(null,null, ex);

            }
        });
    }
    /**
     * This Meeting Add A New Meeting To The System
     * @param id The id of the user.
     * @param token The session token.
     * @param meeting The Meeting To Add
     * @param callback Callback function.
     */
    public void addMeeting(int id,String token,Meeting meeting,Callbacks.General callback){
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("token",token);
        params.put("meeting",meeting);
        makeRequest(Constants.Routes.addMeeting(), params,(json, ex) -> {
            if (ex == null) {
                //OK
                ServerResponse response = new ServerResponse(json);
                if(response.isOK()){
                    //TODO: complete success
                    callback.make(response, null);
                }else{
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response, e);
                }
            }else{
                callback.make(null,ex);
            }
        });
    }
    /**
     * This Method Update The Profile Of This User (user) .
     * @param id The id of the user.
     * @param token The session token.
     * @param user must send it updated
     * @param callback Callback function.
     */
    public void updateUserProfile(int id, String token, User user, final Callbacks.UpdateProfile callback){
        Map<String,Object> params = new HashMap<>();
        params.put("id", id);
        params.put("token", token);
        params.put("user",user);
        makeRequest(Constants.Routes.getProfile(), params, (json, ex) -> {
            if (ex == null) {
                //OK
                ServerResponse response = new ServerResponse(json);
                if(response.isOK()){
                    //Success
                    //Convert jasonObject To User Object
                    JsonObject jsonUser = json.getAsJsonObject("user");
                    User updatedUser = new User().init(jsonUser);
                    callback.make(response,updatedUser, null);
                }else{
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response,null,e);
                }
            }else{
                callback.make(null,null ,ex);
            }
        });
    }
    /**
     * This Method Approve Meeting .
     * @param id The id of the user.
     * @param token The session token.
     * @param meeting_id Meeting Id To Approve Meeting .
     * @param action The Status Of Approving , True Approve Meeting else false .
     * @param callback Callback function.
     */
    public void approveMeeting (int id,String token,String meeting_id,boolean action,Callbacks.General callback){
        Map<String,Object> params=new HashMap<>();
        params.put("id",id);
        params.put("token",token);
        params.put("meeting_id",meeting_id);
        params.put("action",action);
        Log.e("Approving Method","Hashmap "+ params);
        makeRequest(Constants.Routes.approveMeeting(), params, (json, ex) -> {
            Log.e("Approving Method","Make A Request : ");
            if(ex==null){
                ServerResponse response = new ServerResponse(json);
                Log.e("Approving Method","ServerResponse : "+response);
                if(response.isOK()){
                    //TODO: complete success
                    callback.make(response, null);
                    Log.e("Approving Method","ServerResponse Is Ok ");
                }else{
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response, e);
                    Log.e("Approving Method","ServerResponse Isn't Ok : "+response);
                }
            }else{
                callback.make(null, ex);
                Log.e("Approving Method","Error When Request  : "+ex);
            }
        });
    }
    /**
     * This Method Confirm Meeting .
     * @param id The id of the user.
     * @param token The session token.
     * @param meeting_id The Meeting Id To Confirm .
     * @param action The Status Of Confirming , True Confirm Meeting else false .
     * @param callback Callback function.
     */
    public void confirmMeeting(int id,String token,String meeting_id,boolean action,Callbacks.General callback){
        Map<String,Object> params=new HashMap<>();
        params.put("id",id);
        params.put("token",token);
        params.put("meeting_id",meeting_id);
        params.put("action",action);
        makeRequest(Constants.Routes.confirmMeeting(), params, (json, ex) -> {
            if(ex==null){
                ServerResponse response = new ServerResponse(json);
                if(response.isOK()){
                    //TODO: Complete this code
                    callback.make(response,null);
                }else{
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response, e);
                }
            }else{
                callback.make(null, ex);
            }
        });
    }
    /**
     * This Method Get The mentees Of The Mentor
     * @param id The id of the user.
     * @param token The session token.
     * @param callback Callback function.
     */
    private  void getMentees(int id,String token,Callbacks.GetMentees callback){
        Map<String,Object> params=new HashMap<>();
        params.put("id",id);
        params.put("token",token);
            makeRequest(Constants.Routes.getMentees(),params,(json, ex) -> {
                        if (ex == null) {
                            //OK
                            ServerResponse response = new ServerResponse(json);
                            if (response.isOK()) {   //Success
                                //Convert jasonObject To User Object

                                JsonArray obj = json.get("users").getAsJsonArray();
                                ArrayList<User> mentees = new ArrayList<>();
                                for (JsonElement o : obj) {
                                    User m = new User().init(o.getAsJsonObject());
                                    mentees.add(m);
                                }
                                callback.make(response, mentees, null);
                            } else {
                                //Failed
                                //ToDo : handle Code return Specifec Exception
                                ServerException e = new ServerException(response);
                                callback.make(response, null, e);
                            }
                        }else{
                            callback.make(null,null, ex);
                        }
            });

    }
    /**
     * This Method Get Mentor
     * @param id The id of the user.
     * @param token The session token.
     * @param callback Callback function.
     */
    private void getMentor(int id,String token,Callbacks.GetMentor callback){
        Map<String,Object> params=new HashMap<>();
        params.put("id",id);
        params.put("token",token);
        makeRequest(Constants.Routes.getMentor(),params,(json, ex) -> {
            if(ex==null){
                //ok
                ServerResponse response = new ServerResponse(json);
                if (response.isOK()) {   //Success
                    //Success
                    //Convert jasonObject To User Object
                    JsonObject jsonUser = json.getAsJsonObject("user");
                    User user=new User().init(jsonUser);
                    callback.make(response,user, null);
                } else {
                    //Failed
                    //ToDo : handle Code return Specifec Exception
                    ServerException e = new ServerException(response);
                    callback.make(response, null, e);
                }
            }else{
                callback.make(null,null, ex);
            }
        });
    }

    public void getAssociatedUsers(int id,String token,boolean isMentor,Callbacks.GetAssociatedUsers callback){
        if(isMentor){
            getMentees(id, token, (response, users, ex) -> {
                int size = users != null ? users.size() : 0;
                User[] arr = new User[size];

                for (int i = 0; i < size; i++)
                    arr[i] = users.get(i);

                callback.make(response,arr,ex);
            });
        }else{
            getMentor(id,token,(response, user, ex) -> {
                User[] arr = new User[]{user};
                callback.make(response,arr,ex);
            });
        }
    }


    /**
     * This method makes a post HTTP request to a url using the given params.
     *
     * @param url The route to make http request to.
     * @param params The parameters to pass in.
     * @param callback The call back function.
     */
    private void makeRequest(String url, Map<String,Object> params, final Callbacks.Inner callback){
        //define media type
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        //create request body from params

        JsonElement element = convertMapToJson(params);
        Log.i(TAG,"makeRequest: "+url);
        Log.i(TAG, "makeRequest: \n"+ element.toString());


        RequestBody body = RequestBody.create(mediaType,element.toString());
        //create request
        Request request = new Request
                .Builder()
                .url(url)
                .post(body)
                .addHeader("content-type","application/json")
                .build();
        //make request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null)
                    callback.make(null,e);
                Log.i(TAG, "onFailure: " + e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (callback!= null){
                    try (ResponseBody responseBody = response.body()) {
                        String res = responseBody.string();
                        Log.i(TAG, "onResponse: " + res);
                        try{
                            JsonParser parser = new JsonParser();
                            JsonObject o = parser.parse(res).getAsJsonObject();
                            callback.make(o,null);
                        }catch (Exception e) {
                            callback.make(null, e);
                        }
                        responseBody.close();
                    }
                }
            }
        });
    }
    /**
     * Helper function, used to convert map to json object.
     * @param params The parameters.
     * @return A json object containing these parameters.
     */
    private JsonObject convertMapToJson(Map<String,Object> params){
        JsonObject object = new JsonObject();
        if (params != null) {
            for (Map.Entry<String, Object> item : params.entrySet()) {

                //boolean support
                if (item.getValue() instanceof Boolean)
                    object.addProperty(item.getKey(), (Boolean) item.getValue());

                //number support
                if (item.getValue() instanceof Number)
                    object.addProperty(item.getKey(), (Number) item.getValue());

                //string support
                if (item.getValue() instanceof String)
                    object.addProperty(item.getKey(), (String) item.getValue());

                //char support
                if (item.getValue() instanceof Character)
                    object.addProperty(item.getKey(), (Character) item.getValue());

                //map support
                if (item.getValue() instanceof Map)
                    object.add(item.getKey(),convertMapToJson((Map<String,Object>) item.getValue()));

                //mappable support
                if(item.getValue() instanceof Mappable)
                    object.add(item.getKey(),convertMapToJson(((Mappable) item.getValue()).map()));
            }
        }
        return object;
    }
}
