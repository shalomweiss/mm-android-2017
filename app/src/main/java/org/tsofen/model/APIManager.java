package org.tsofen.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.tsofen.model.classes.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hamza on 13-Sep-17.
 */

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

    /**
     * Private constructor
     */
    private APIManager(){
        client = new OkHttpClient();
    }

    /**
     * Login function. This method is used when we want to sign in to the application.
     * This will return a session token and a user object.
     *
     * @param email The email of the account.
     * @param password The password of the account.
     * @param callback The callback function.
     */
    public void login(String email,String password,final Callbacks.Auth callback){

        Map<String,Object> params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);

        makeRequest(Constants.Routes.login(), params, (json, ex) -> {
            if (ex == null) {
                //OK

                //TODO: parse json to User
                User user = new User();
                String token = json.get("token").getAsString();

                callback.make(user,token,null);

            }else{
                callback.make(null,null,ex);
            }
        });
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
        RequestBody body = RequestBody.create(mediaType,convertMapToJson(params).toString());

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
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonParser parser = new JsonParser();
                JsonObject o = parser.parse(response.body().string()).getAsJsonObject();
                if (callback != null)
                    callback.make(o,null);
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
                if (item.getValue() instanceof Boolean)
                    object.addProperty(item.getKey(), (Boolean) item.getValue());


                if (item.getValue() instanceof Number)
                    object.addProperty(item.getKey(), (Number) item.getValue());


                if (item.getValue() instanceof String)
                    object.addProperty(item.getKey(), (String) item.getValue());


                if (item.getValue() instanceof Character)
                    object.addProperty(item.getKey(), (Character) item.getValue());
            }
        }

        return object;
    }




}
