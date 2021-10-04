package com.example.flightsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class FlightManageActivity extends AppCompatActivity {
    private ArrayList<OrderData> mList;
    private LinearLayoutManager mLinearLayoutManager;
    private MyRecycleView2Adapter mAdapter;
    private RecyclerView mRecycleView;

    private MyRecycleView2Adapter.OnItemClickListener MyItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_manage);
        mRecycleView = findViewById(R.id.flight_manage_list);
        MyItemClickListener = new MyRecycleView2Adapter.OnItemClickListener() {
            @Override
            public void onSiteButtonClick(int position) {
                //TODO 弹窗提示选座位
                SiteDialog.Builder builder = new SiteDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Choose Site");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String site = builder.getSite();
                        Toast.makeText(getApplicationContext(), "reserve"+ site + "succeed",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.create().show();
            }

            @Override
            public void onServiceButtonClick(int position) {
                //TODO 弹窗提示选服务
                ServiceDialog.Builder builder = new ServiceDialog.Builder(FlightManageActivity.this);
                builder.setTitle("Choose Service");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String site = builder.getSite();
                        Toast.makeText(getApplicationContext(), "reserve"+ site + "succeed",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.create().show();
            }
        };
        initView();
        initData();
    }

    private void initView() {
        mList = new ArrayList<OrderData>();
        mLinearLayoutManager = new LinearLayoutManager(FlightManageActivity.this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyRecycleView2Adapter(mList);
        mRecycleView.setLayoutManager(mLinearLayoutManager);
        mAdapter.setOnItemClickListener(MyItemClickListener);
        mRecycleView.setAdapter(mAdapter);
    }

    private void initData(){
        mList.clear();
        mList.add(new OrderData(1,"NH1999","17:00-19:00","",""));
    }
}