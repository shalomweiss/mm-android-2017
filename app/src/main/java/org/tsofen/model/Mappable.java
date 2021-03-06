package org.tsofen.model;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by minitour on 01/10/2017.
 */

public interface Mappable<T> extends Serializable {

    /**
     * A method that converts the current object to a map.
     * @return A map containing the values of the object <Key,Value>
     */
    Map<String,Object> map();

    /**
     * A method that modifies the current object from a json object.
     * @param o The json object which contains the data.
     */
    T init(JsonObject o);

    /**
     * A method that modifies the current object using a hash map.
     * @param o The map containing the values.
     */
    T init(Map<String,Object> o);
}
