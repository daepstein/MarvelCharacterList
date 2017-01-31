package com.daepstein.marvel.marvelcharacters.util;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;


/**
 * Created by daepstein on 1/30/2017.
 */

public class AskPermission {


    private static final int REQUEST_READ_WRITE_FILES = 1;
    private static String[] PERMISSION_FILES = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public boolean isPermitted() {
        int permissionToRead = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionToWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return permissionToRead == PackageManager.PERMISSION_GRANTED && permissionToWrite == PackageManager.PERMISSION_GRANTED;
    }

    private Activity activity;

    private void setActivity(Activity activity) {
        this.activity = activity;
    }


    public AskPermission(Activity activity){
        this.setActivity(activity);
    }

    public void askReadWrite(){
        int permissionToRead = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionToWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionToRead != PackageManager.PERMISSION_GRANTED || permissionToWrite != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_FILES,
                    REQUEST_READ_WRITE_FILES
            );
        }
    }


}
