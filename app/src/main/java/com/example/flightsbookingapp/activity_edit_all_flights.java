package com.example.flightsbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_edit_all_flights extends AppCompatActivity {
    RecyclerView recView;
    DatabaseReference databaseReference;
    user_adapter user_adapter;
    private FirebaseAuth ref = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_all_flights);
        FirebaseUser s = ref.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        recView = findViewById(R.id.user_recV);
        recView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<User> options =
                new  FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), User.class)
                        .build();
        user_adapter=new user_adapter(options);
        recView.setAdapter(user_adapter);
    }

    @Override
    protected void onStart() {

        super.onStart();
        user_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        user_adapter.stopListening();
    }
}