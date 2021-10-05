package com.example.flightsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;

public class FligthSearchActivity extends AppCompatActivity {
    private ArrayList<FlightData> mList;
    private LinearLayoutManager mLinearLayoutManager;
    private MyRecycleViewAdapter mAdapter;
    private RecyclerView mRecycleView;
    private MyRecycleViewAdapter.OnItemClickListener MyItemClickListener = new MyRecycleViewAdapter.OnItemClickListener() {
        @Override
        public void onSubmitButtonClick(int position) {
            final AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(FligthSearchActivity.this);
            normalDialog.setTitle("Order");
            normalDialog.setMessage("Whether to confirm the order");
            normalDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(FligthSearchActivity.this,FlightManageActivity.class);
                    intent.putExtra("flightInfo",mList.get(position).getInfo());
                    startActivity(intent);
                }
            });

            normalDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO 直接返回
                }
            });

            normalDialog.show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fligth_search);
        mRecycleView = findViewById(R.id.flight_search_list);
        initView();
        initData();
    }

    private void initView() {
        mList = new ArrayList<FlightData>();
        mLinearLayoutManager = new LinearLayoutManager(FligthSearchActivity.this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyRecycleViewAdapter(mList);
        mRecycleView.setLayoutManager(mLinearLayoutManager);
        mAdapter.setOnItemClickListener(MyItemClickListener);
        mRecycleView.setAdapter(mAdapter);
    }

    public void initData() {
        //TODO 从文件中读取数据然后在list中显示
        mList.clear();
        mList.add(new FlightData("19:00","21:00","Beijing","Shanghai","NH1999"));
    }

}