package com.worldmer.telephonymanagerdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvPhoneDetails;
    Button btnGetDetails;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        tvPhoneDetails = findViewById(R.id.tvphonedetails);
        btnGetDetails = findViewById(R.id.btnGetDetails);
        btnGetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
                if (status != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
                } else {
                    tvPhoneDetails.setText(getDeviceInfo());
                }
            }
        });
    }

    public String getDeviceInfo() {
        String details = null;
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        details =
                "DeviceSoftwareVersion : " + manager.getDeviceSoftwareVersion() + "\n"
                        + "GroupIdLevel1 : " + manager.getGroupIdLevel1() + "\n"
                        + "Line1Number : " + manager.getLine1Number() + "\n"
                        + "MmsUAProfUrl : " + manager.getMmsUAProfUrl() + "\n"
                        + "MmsUserAgent : " + manager.getMmsUserAgent() + "\n"
                        + "DeviceId : " + manager.getDeviceId() + "\n"
                        + "SimSerialNumber : " + manager.getSimSerialNumber() + "\n"
                        + "SimCountryIso : " + manager.getSimCountryIso() + "\n"
                        + "NetworkOperator() : " + manager.getNetworkOperator() + "\n"
                        + "NetworkCountryIso : " + manager.getNetworkCountryIso() + "\n"
                        + "NetworkOperatorName : " + manager.getNetworkOperatorName() + "\n"
                        + "NetworkType : " + manager.getNetworkType() + "\n"
                        + "VoiceMailNumber : " + manager.getVoiceMailNumber();
        Log.d("TeleInfo",details);
        return details;
    }
}