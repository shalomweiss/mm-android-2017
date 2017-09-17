package org.tsofen.model;

/**
 * Created by win10 on 17/09/2017.
 */

public class ServerException extends Exception {
    private int code;
    public ServerException(String message,int code){
        super(message);
            this.code=code;
    }

}
