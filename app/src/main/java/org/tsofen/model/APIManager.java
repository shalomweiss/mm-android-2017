package org.tsofen.model;

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

    private final static APIManager manager = new APIManager();

    private APIManager(){}


}
