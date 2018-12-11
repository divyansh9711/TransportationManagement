package com.example.divyanshsingh.transportationmanagement.acitivity;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.divyanshsingh.transportationmanagement.BuildConfig;

/**
 * Created By Divyansh Singh
 */
public class AppController {

    public static SharedPreferences prefs;
    public static final String PREFS_NAME = "CAMPUSMANAGER_PREFS";

    public AppController(Context context){
        prefs = context.getSharedPreferences(PREFS_NAME + BuildConfig.VERSION_CODE, Context.MODE_PRIVATE);
    }
    public SharedPreferences getPrefs(){
        return prefs;
    }
}
