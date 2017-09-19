package org.tsofen.model;

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

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
