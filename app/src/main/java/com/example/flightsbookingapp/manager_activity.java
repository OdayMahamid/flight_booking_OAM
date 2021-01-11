package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class manager_activity extends AppCompatActivity implements View.OnClickListener, dialog.ExampleDialogListener{
    Button add ;
    Button delete;
    Button profile, edit_flight_button,cust_button;
    String manager_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        add=(Button) findViewById(R.id.addflight);
        delete=(Button) findViewById(R.id.Edit_flight);
        profile=(Button) findViewById(R.id.profile_button);
        edit_flight_button = findViewById(R.id.cust_button);
        cust_button=findViewById(R.id.cust_button);
        manager_id=getIntent().getStringExtra("manager_id");

        edit_flight_button.setOnClickListener(this);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        profile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addflight)
        {
            Intent intent = new Intent(manager_activity.this,FlightDetails.class);
            intent.putExtra("manager_id", manager_id);
            startActivity(intent);
        }
        else if(v.getId() == R.id.Edit_flight)
        {
            Intent intent = new Intent(manager_activity.this,activity_edit_all_flights.class);
            intent.putExtra("manager_id", manager_id);
            startActivity(intent);
        }
        else if(v.getId() ==R.id.profile_button)
        {
            dialog exampleDialog = new dialog ();
            exampleDialog.show(getSupportFragmentManager(), "edit profile");
        }
        else if(v.getId() ==R.id.cust_button)
        {
            Intent intent = new Intent(manager_activity.this,Activity_cus_flights.class);
            intent.putExtra("manager_id", manager_id);
            startActivity(intent);
        }

    }
    @Override
    public void applyTexts(String username, String password) {

    }
}
