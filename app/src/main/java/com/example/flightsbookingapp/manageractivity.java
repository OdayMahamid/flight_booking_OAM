package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class manageractivity  extends AppCompatActivity implements View.OnClickListener{
    Button add ;
    Button delete;
    Button profile;
    String manager_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        add=(Button) findViewById(R.id.addflight);
        delete=(Button) findViewById(R.id.deleteflight);
        profile=(Button) findViewById(R.id.profile_button);
        manager_id=getIntent().getStringExtra("manager_id");
        System.out.println("activity"+manager_id);


        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addflight)
        {
            Intent intent = new Intent(manageractivity.this,FlightDetails.class);
            intent.putExtra("manager_id", manager_id);
            startActivity(intent);
        }
        else if(v.getId() == R.id.deleteflight)
        {
            Intent intent = new Intent(manageractivity.this,Delete_Flight.class);
            intent.putExtra("manager_id", manager_id);
            startActivity(intent);
        }
        else if(v.getId() ==R.id.profile_button)
        {
            Intent intent=new Intent(manageractivity.this,manager_profile.class);
            intent.putExtra("manager_id", manager_id);
            startActivity(intent);
        }

    }
}
