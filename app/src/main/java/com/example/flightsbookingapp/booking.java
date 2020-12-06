package com.example.flightsbookingapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class booking extends AppCompatActivity {

    RecyclerView recView;
    DatabaseReference databaseReference;
    myadapter adapter;

    private FirebaseAuth ref = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        FirebaseUser s = ref.getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference("flights");
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<flights> options =
                new  FirebaseRecyclerOptions.Builder<flights>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("flights"), flights.class)
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
