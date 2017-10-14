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
    public final static String address = "zofenweb-env.hhaqjxq9wa.us-east-2.elasticbeanstalk.com";

    /**
     * Port
     */
    public final static String port = "80";

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
            return getAddress() + "/LogIn";
        }
        public static String updateProfile(){return getAddress() + "/updateProfile"; }
        public static String getProfile(){
            return getAddress() + "/GetProfile";
        }
        public static String getMeetings() { return getAddress() + "/GetMeetings";}
        public static String getMeetingById() { return getAddress() + "/GetMeetingById";}
        public static String addMeeting() { return getAddress() + "/AddMeeting";}
        public static String approveMeeting() { return getAddress() + "/ApproveMeeting";}
        public static String confirmMeeting() { return getAddress() + "/confirmMeeting";}
        public static String getMentees() { return getAddress() + "/GetMentees";}
        public static String getMentor() { return getAddress() + "/GetMentor";}
    }
    public static class Codes{
        public static final int SUCCESS=200;
        public static final int MISSING_PARAMETERS=401;

    }

}
