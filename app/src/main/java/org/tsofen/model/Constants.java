package org.tsofen.model;

/**
 * Created by minitour on 13/09/2017.
 */

final public class Constants {

    /**
     * Protocol
     */
    public final static String protocol = "http";

    /**
     * Address
     */
    public final static String address = "127.0.0.1";

    /**
     * Port
     */
    public final static String port = "8080";

    /**
     * @return full path
     */
    public final static String getAddress(){
        return protocol + "://" + address + ":" + port;
    }

    /**
     * Routes class, contains all API routes.
     */
    public static class Routes {

        public static String login(){
            return getAddress() + "/login";
        }
    }
}
