package com.sparktech.abidematrimony.splashscreen.presenter;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.sparktech.abidematrimony.Landing.model.DefaultDetails;
import com.sparktech.abidematrimony.common.Helper;
import com.sparktech.abidematrimony.splashscreen.view.ISplashView;

/**
 * Created by User on 8/28/2017.
 */

public class SplashPresenter implements IConnectionStatus {

    Context context;
    ISplashView splashView;
    Activity activity;
    String emailAddress;
    Boolean firstTime = true;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    private int SPLASH_DISPLAY_LENGTH = 1000;

    public SplashPresenter(Context context, ISplashView splashView, Activity activity, SharedPreferences sharedPrefs, SharedPreferences.Editor editor) {
        this.activity = activity;
        this.context = context;
        this.splashView = splashView;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
       getUserGsonInSharedPrefrences();
    }

    public void getUserGsonInSharedPrefrences() {
        Gson gson = new Gson();
        String json = sharedPrefs.getString(Helper.DEFAULT_DETAILS, null);
        if (json != null) {
            DefaultDetails defaultDetails = gson.fromJson(json, DefaultDetails.class);
            firstTime = defaultDetails.isFirstTime();
            emailAddress = defaultDetails.getEmail();
            System.out.println("--------- SplashPresenter getUserGsonInSharedPrefrences" + json);
        }

    }

    @Override
    public void load() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!firstTime) {
                    if (emailAddress != null) {
                        splashView.moveToLanding();
                    } else {
                        splashView.moveToSignUpLogin();
                    }

                } else {
                    splashView.moveToWalkThrough();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
