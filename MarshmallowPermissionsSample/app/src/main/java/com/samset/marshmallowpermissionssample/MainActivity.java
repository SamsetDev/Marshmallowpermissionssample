package com.samset.marshmallowpermissionssample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ_PHONE_STATE_PERMISSION = 225;
    private static final int REQUEST_READ_EXTERNALSTORAGE_PERMISSION = 226;
    private static final int REQUEST_WRITE_EXTERNALSTORAGE_PERMISSION = 227;

    private String TAG=MainActivity.class.getSimpleName();
    private MainActivity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx=this;
        checkPermission();
    }

    public void checkPermission() {

        //get all required elements
        int READ_PHONE_PERMISSION=ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE);
        int READ_EXTERNALSTORAGE_PERMISSION=ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNALSTORAGE_PERMISSION=ContextCompat.checkSelfPermission(ctx, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        //Check permissions if permission already granted run if blog if not granted then run else blog
        if (READ_PHONE_PERMISSION == PackageManager.PERMISSION_GRANTED && READ_EXTERNALSTORAGE_PERMISSION == PackageManager.PERMISSION_GRANTED && WRITE_EXTERNALSTORAGE_PERMISSION == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, " Permission Already given ");
            telephonePermission();

        } else {
            Log.d(TAG, "Current app does not have permission");
            ActivityCompat.requestPermissions(ctx,new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_READ_PHONE_STATE_PERMISSION);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e(TAG, "Permission request" + requestCode);


        switch (requestCode) {

            case REQUEST_READ_PHONE_STATE_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Permission Granted");
                    //Proceed to next steps
                    telephonePermission();


                }
                else {
                    Log.e(TAG, "Permission Denied");
                }
                return;
            }
            case REQUEST_READ_EXTERNALSTORAGE_PERMISSION:
            {
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Permission Granted");
                    //Proceed to next steps
                    telephonePermission();


                }
                else {
                    Log.e(TAG, "Permission Denied");
                }
                return;
            }
            case REQUEST_WRITE_EXTERNALSTORAGE_PERMISSION:
            {
                if (grantResults.length > 0 && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "Permission Granted");
                    //Proceed to next steps
                    telephonePermission();


                }
                else {
                    Log.e(TAG, "Permission Denied");
                }
                return;
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void telephonePermission() {
        TelephonyManager TM = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String androidId = android.provider.Settings.Secure.getString(this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);


    }

}
