package com.example.flightsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FlightManageActivity extends AppCompatActivity {
    private TextView UserInfo;
    private TextView SiteInfo;
    private TextView ServiceInfo;
    private String UserInfoText;
    private String FlightInfoText;
    private String SiteInfoText;
    private String ServiceInfoText;

    private Button SiteBtn;
    private Button ServiceBtn;

    private Button SubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_manage);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.keySet().contains("flightInfo")){
            FlightInfoText = savedInstanceState.getString("flightInfo");
        }

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
                        Toast.makeText(getApplicationContext(), "reserve"+ SiteInfoText + "succeed",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "reserve"+ ServiceInfoText + "succeed",Toast.LENGTH_SHORT).show();
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
                MessageDialog.Builder builder = new MessageDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Check Information");
                builder.setUserInfo(UserInfoText);
                builder.setFlightInfo(FlightInfoText);
                builder.setSiteInfo(SiteInfoText);
                builder.setServiceInfo(ServiceInfoText);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });
    }
}