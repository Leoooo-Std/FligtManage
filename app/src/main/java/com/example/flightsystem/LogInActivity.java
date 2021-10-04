package com.example.flightsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    private TextView UserName;
    private TextView Id;
    private TextView PhoneNumber;
    private Button SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        UserName = findViewById(R.id.username);
        Id = findViewById(R.id.id);
        PhoneNumber = findViewById(R.id.phonenumber);
        SignIn = findViewById(R.id.login);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = UserName.getText().toString();
                String id = Id.getText().toString();
                String phonenumber = PhoneNumber.getText().toString();

                if(username.isEmpty() || id.isEmpty() || phonenumber.isEmpty()){
                    Toast.makeText(LogInActivity.this,"Please Input All Informantion",Toast.LENGTH_SHORT).show();
                    return;
                }

                UserData.setUsername(username);
                UserData.setId(id);
                UserData.setPhonenumber(phonenumber);

                Intent intent = new Intent();
                intent.setClass(LogInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}