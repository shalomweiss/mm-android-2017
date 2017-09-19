package org.tsofen.model.classes;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by minitour on 13/09/2017.
 */

    public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public User(){

    };

    public Map<String,Object> getHashedUser(){
        Map<String,Object> hashedUser = new HashMap<String,Object>();
        hashedUser.put("firstName", firstName);
        hashedUser.put("lastName", lastName);
        hashedUser.put("email", email);
        hashedUser.put("phoneNumber", phoneNumber);
        hashedUser.put("address", address);
        return hashedUser;
    };

    public User(JsonObject jsonUser) {
        if(jsonUser.get("id")!=null){
            setId(jsonUser.get("id").getAsInt());
        }
        if(jsonUser.get("firstName")!=null){
            setFirstName(jsonUser.get("firstName").getAsString());
        }
        if(jsonUser.get("lastName")!=null){
            setFirstName(jsonUser.get("lastName").getAsString());
        }
        if(jsonUser.get("email")!=null){
            setFirstName(jsonUser.get("email").getAsString());
        }
        if(jsonUser.get("phoneNumber")!=null){
            setFirstName(jsonUser.get("phoneNumber").getAsString());
        }
        if(jsonUser.get("address")!=null){
            setFirstName(jsonUser.get("address").getAsString());
        }

    }

    public int getId(){
        return this.id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getAddress(){
        return this.address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User[ID : "+this.id+" ,First Name : "+firstName+" ,Last Name : "+lastName+" ,Email : "+email+
                " ,Phone Number : "+phoneNumber+" ,Address : "+address+"]";
    }
}
