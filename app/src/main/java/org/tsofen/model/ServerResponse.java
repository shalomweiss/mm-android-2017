package org.tsofen.model;

import com.google.gson.JsonObject;

/**
 * Created by ayman on 9/18/2017.
 */

public class ServerResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    void setCode(int code) {
        this.code = code;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServerResponse(JsonObject json){
        code = json.get("code").getAsInt();
        message = json.get("message").getAsString();
    }

    public boolean isOK(){
        return getCode() == Constants.Codes.SUCCESS;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
