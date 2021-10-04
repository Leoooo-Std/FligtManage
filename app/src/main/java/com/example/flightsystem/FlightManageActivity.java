package com.example.flightsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
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

    private Button SiteBtn;
    private Button ServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_manage);

        UserInfo = findViewById(R.id.user_text);
        SiteInfo = findViewById(R.id.site_text);
        ServiceInfo = findViewById(R.id.service_text);

        SiteBtn = findViewById(R.id.site_btn);
        ServiceBtn = findViewById(R.id.service_btn);

        SiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiteDialog.Builder builder = new SiteDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Choose Site");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String site = builder.getSite();
                        UserInfo.setText("Your Site Info: " + site);
                        Toast.makeText(getApplicationContext(), "reserve"+ site + "succeed",Toast.LENGTH_SHORT).show();
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
                        String service = builder.getService();
                        ServiceInfo.setText("Your Service Info: " + service);
                        Toast.makeText(getApplicationContext(), "reserve"+ service + "succeed",Toast.LENGTH_SHORT).show();
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
    }
}