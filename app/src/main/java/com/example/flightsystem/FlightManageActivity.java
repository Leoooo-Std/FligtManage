package com.example.flightsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FlightManageActivity extends AppCompatActivity {
    private TextView UserInfo;
    private TextView SiteInfo;
    private TextView ServiceInfo;
    private String UserInfoText;
    private String FlightName;
    private String FlightStartTime;
    private String FlightEndTime;
    private String FlightStartCity;
    private String FlightEndCity;
    private String FlightInfoText;
    private String SiteInfoText = "";
    private String ServiceInfoText = "";

    private Button SiteBtn;
    private Button ServiceBtn;

    private Button SubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_manage);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("flightName")){
            FlightName = (String)bundle.get("flightName");
        }

        if(bundle != null && bundle.containsKey("flightStartTime")){
            FlightStartTime = (String)bundle.get("flightStartTime");
        }

        if(bundle != null && bundle.containsKey("flightEndTime")){
            FlightEndTime = (String)bundle.get("flightEndTime");
        }

        if(bundle != null && bundle.containsKey("flightStartCity")){
            FlightStartCity = (String)bundle.get("flightStartCity");
        }

        if(bundle != null && bundle.containsKey("flightEndCity")){
            FlightEndCity = (String)bundle.get("flightEndCity");
        }

        FlightInfoText = FlightName + "\n" + FlightStartTime + "-" + FlightEndTime + "\n"
                        + FlightStartCity + "-" + FlightEndCity;

        UserInfo = findViewById(R.id.user_text);

        UserInfoText = "\tUsername: " + UserData.getUsername() + "\n"
                        + "\tId: " + UserData.getId() + "\n"
                        + "\tPhoneNumber: " + UserData.getPhonenumber();

        UserInfo.setText(UserInfoText);

        SiteInfo = findViewById(R.id.site_text);
        ServiceInfo = findViewById(R.id.service_text);

        SiteBtn = findViewById(R.id.site_btn);
        ServiceBtn = findViewById(R.id.service_btn);
        SubmitBtn = findViewById(R.id.submit_btn);

        SiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteDialog.Builder builder = new SiteDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Choose Site");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SiteInfoText = builder.getSite();
                        SiteInfo.setText("\tYour Site Info: " + SiteInfoText);
                        Toast.makeText(getApplicationContext(), SiteInfoText,Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        ServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceDialog.Builder builder = new ServiceDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Choose Service");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ServiceInfoText = builder.getService();
                        ServiceInfo.setText("\tYour Service Info: " + ServiceInfoText);
                        Toast.makeText(getApplicationContext(), ServiceInfoText,Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SiteInfoText.isEmpty() || ServiceInfoText.isEmpty()){
                    Toast.makeText(FlightManageActivity.this,
                            "Please Input All Information",Toast.LENGTH_LONG).show();
                    return;
                }
                MessageDialog.Builder builder = new MessageDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Check Information");
                builder.setUserInfo(UserInfoText);
                builder.setFlightInfo(FlightInfoText);
                builder.setSiteInfo(SiteInfoText);
                builder.setServiceInfo(ServiceInfoText);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] siteInfos = SiteInfoText.split(":");
                        String[] serviceInfos = ServiceInfoText.split(":");
                        String str = UserData.getUsername() + "," + UserData.getPhonenumber() + ","
                                   + UserData.getId() + "," + FlightName + "," + FlightStartTime + ","
                                   + FlightEndTime + "," + FlightStartCity + "," + FlightEndCity + ","
                                   + siteInfos[0] + "," + siteInfos[1] + "," + serviceInfos[0] + ","
                                   + serviceInfos[1];
                        writeToFile(str);
                        Intent intent = new Intent();
                        intent.setClass(FlightManageActivity.this,LastActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
    }

    void writeToFile(String str){
        FileOutputStream fout = null;
        try{
            fout = openFileOutput("order.csv",MODE_APPEND);
            fout.write(str.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}