package org.tsofen;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import org.tsofen.mentorim.R;
import org.tsofen.model.DataManager;
import org.tsofen.model.classes.User;

/**
 * Created by minitour on 28/09/2017.
 */

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
