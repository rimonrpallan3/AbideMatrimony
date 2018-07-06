package com.sparktech.abidematrimony.splashscreen;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sparktech.abidematrimony.Landing.Landing;
import com.sparktech.abidematrimony.R;
import com.sparktech.abidematrimony.SignInPage.SignInPage;
import com.sparktech.abidematrimony.common.Helper;
import com.sparktech.abidematrimony.signuppage.SignUpPage;
import com.sparktech.abidematrimony.splashscreen.presenter.SplashPresenter;
import com.sparktech.abidematrimony.splashscreen.view.ISplashView;
import com.sparktech.abidematrimony.walkthrough.WalkThrough;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;



/**
 * Created by User on 8/23/2017.
 */

public class SplashScreen extends AppCompatActivity implements ISplashView,EasyPermissions.PermissionCallbacks{

    private SplashPresenter mPresenter;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        sharedPrefs = getSharedPreferences(Helper.DEFAULT_DETAILS,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        mPresenter = new SplashPresenter(this, this, this, sharedPrefs, editor);
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mPresenter.load();
        }else {
            mPresenter.load();
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void moveToSignUpLogin() {
        Intent intent = new Intent(this, SignInPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void moveToLanding() {
        Intent intent = new Intent(this, Landing.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void moveToWalkThrough() {
        Intent intent = new Intent(this, WalkThrough.class);
        startActivity(intent);
        finish();
    }
}