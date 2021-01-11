package com.example.flightsbookingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class single_row extends AppCompatActivity {
    Button btn;
    FirebaseAuth ref=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);
        btn = findViewById(R.id.cust_button);
        btn.setOnClickListener(view -> {
            Log.d("", "onCreate: " + ref.getCurrentUser().getUid());
            DatabaseReference ref1= FirebaseDatabase.getInstance().getReference(ref.getCurrentUser().getUid());
            ref1.child(ref.getCurrentUser().getUid()).setValue("lol");
        });
    }

}
