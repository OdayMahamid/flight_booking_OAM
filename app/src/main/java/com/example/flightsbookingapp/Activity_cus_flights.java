package com.example.flightsbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_cus_flights extends AppCompatActivity {
    RecyclerView recView;
    DatabaseReference databaseReference;
    private FirebaseAuth ref = FirebaseAuth.getInstance();
    myadapter adapter;
    FirebaseAuth auth=FirebaseAuth.getInstance();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_flights);
        FirebaseUser s = ref.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(getIntent().getStringExtra("user_email"));
        recView = findViewById(R.id.cust_recV);
        recView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<flights> options =
                new  FirebaseRecyclerOptions.Builder<flights>()
                        .setQuery(databaseReference.child("user_flights"), flights.class)
                        .build();
        adapter=new myadapter(options);
        recView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}