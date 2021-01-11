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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class Activity_cus_flights extends AppCompatActivity {
    RecyclerView recView;
    DatabaseReference databaseReference;
    DatabaseReference custReference;

    private FirebaseAuth ref = FirebaseAuth.getInstance();
    myadapter adapter;
    String manager_id;

    LinkedList<flights> flightslist;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_flights);
        FirebaseUser s = ref.getCurrentUser();
        manager_id=getIntent().getStringExtra("manager_id");

//
//        databaseReference=FirebaseDatabase.getInstance().getReference("Managers").child(manager_id).child("flights");
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    String fromflight = (String) postSnapshot.child("from").getValue().toString();
//                    String toflight= (String) postSnapshot.child("to").getValue().toString();
//                    String dateflight= (String) postSnapshot.child("date").getValue().toString();
//                    String deptime=(String) postSnapshot.child("dep_time").toString();
//                    String landtime=(String) postSnapshot.child("lan_time").toString();
//                    String price=(String) postSnapshot.child("price").toString();
//                    flights f=new flights(fromflight,toflight,dateflight,deptime,landtime,price,0);
//                    flightslist.add(f);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        custReference=FirebaseDatabase.getInstance().getReference("users").child("user_cart");


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