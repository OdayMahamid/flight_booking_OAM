package com.example.flightsbookingapp;

import android.content.Intent;
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
        cust_button = (Button)findViewById(R.id.login_button);
        manager_button=(Button)findViewById(R.id.managerbutton);

        cust_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), custlogin.class);
                startActivity(intent);
            }
        });

        manager_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(getApplicationContext(), managerlogin.class);
                startActivity(myintent);
            }
        });


    }




}