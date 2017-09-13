package org.tsofen.mentorim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import org.tsofen.model.APIManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Map<String,Object> objectMap = new HashMap<>();
//        objectMap.put("param1","value");
//        objectMap.put("param2",20);
//        APIManager.getInstance().test(null, new APIManager.InnerCallback() {
//            @Override
//            public void callback(JsonObject json, IOException execption) {
//
//                if (execption == null ) Log.i("main",json.toString());
//                else execption.printStackTrace();
//            }
//        });
    }
}
