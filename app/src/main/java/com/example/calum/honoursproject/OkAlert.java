package com.example.calum.honoursproject;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Calum on 10/10/2015.
 */
public class OkAlert {
    AlertDialog.Builder alert;

    public OkAlert(Context context, String title, String message) {

        alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    public void show() {
        alert.show();
    }
}
