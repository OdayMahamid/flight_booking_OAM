package com.example.flightsbookingapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {


    private Button cust_button;
    private Button manager_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cust_button = findViewById(R.id.login_button);
        manager_button= findViewById(R.id.managerbutton);
        inter_receiver my_receiver= new inter_receiver();
        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(my_receiver, intentFilter);
        cust_button.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), custlogin.class);
            startActivity(intent);
        });

        manager_button.setOnClickListener(v -> {
            Intent myintent=new Intent(getApplicationContext(), managerlogin.class);
            startActivity(myintent);
        });


    }




}