package com.example.calum.honoursproject;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by Calum on 04/04/2015.
 * Initialise parse here!
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "nFHu3bnj37q5vzWkItvJutUFOPMLwjC1HbiiAXiC", "TO9nrLJAi0w7X9pipEMjf1XgftWgfkAnuKkdhq6e");

        Log.w("App", "Parse initialised");
    }
}
