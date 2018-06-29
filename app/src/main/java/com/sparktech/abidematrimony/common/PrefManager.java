package com.sparktech.abidematrimony.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class is used to record the first launch of the application in the shared preference.
 * Based on preferences, if the application is launched for the very first time, the Walkthrough screens are shown.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "DefaultDetails";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * This method is responsible for setting up the application first launch value in the preferences.
     * @param isFirstTime   Boolean value determining the first launch of the applicaiton,
     *                      True = First time launch of application,
     *                      False = Application is not launched for the first time.
     */
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    /**
     * This method is used to rest the fist launch state
     * @return  Boolean value for reset
     */
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
