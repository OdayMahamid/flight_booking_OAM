package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class booking extends AppCompatActivity {

    RecyclerView recView;
    DatabaseReference databaseReference;
    myadapter adapter;
    ImageView cart_image, search_button;
    EditText search_edit;
    FirebaseRecyclerOptions<flights> options;
    private static final String[] countries = new String[]{"China", "Italy", "Armenia", "israel", "England", "Germany", "Algeria","Paris","dubai"};
    private FirebaseAuth ref = FirebaseAuth.getInstance();
    private search_adapter search_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        FirebaseUser s = ref.getCurrentUser();
        cart_image = findViewById(R.id.car_button);
        databaseReference = FirebaseDatabase.getInstance().getReference("flights");
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        options =
                new  FirebaseRecyclerOptions.Builder<flights>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("flights"), flights.class)
                .build();
        adapter=new myadapter(options);
        recView.setAdapter(adapter);
        cart_image.setOnClickListener( n -> {
            Intent myIntent = new Intent(this, activity_customer_cart.class);
            startActivity(myIntent);
        });

        search_button = findViewById(R.id.search_button);
        search_edit = findViewById(R.id.search_edit);

        final String[] sour = new String[1];

        search_button.setOnClickListener(v -> {
            sour[0] = search_edit.getText().toString().trim();
            if(sour[0].equals("")){
                recView.setAdapter(adapter);
            }
            else {
                search_adapter = new search_adapter(sour[0]);
                search_edit.setText(sour[0]);
                recView.setAdapter(search_adapter);
            }
        });

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
