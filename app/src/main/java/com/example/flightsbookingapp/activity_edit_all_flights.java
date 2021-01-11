package com.example.flightsbookingapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_edit_all_flights extends AppCompatActivity implements View.OnClickListener{

    private String manager_id;
    private RecyclerView recyclerView;
    private TextView title;
    private ImageButton back;
    private FirebaseDatabase db;
    private DatabaseReference manageref;
    private flightsadabter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_all_flights);
        back=findViewById(R.id.back_button);
        title=findViewById(R.id.title_text);
        manager_id=getIntent().getStringExtra("manager_id");

        // Create a instance of the database and get
        // its reference
        manageref = FirebaseDatabase.getInstance().getReference("Managers").child(manager_id).child("flights");
        recyclerView = findViewById(R.id.recycler_flights);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<flights> options
                = new FirebaseRecyclerOptions.Builder<flights>()
                .setQuery(manageref, flights.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new flightsadabter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        db=FirebaseDatabase.getInstance();
        manageref=db.getReference("Managers").child(manager_id);
        manageref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Manager m = snapshot.getValue(Manager.class);
//                name.setText(snapshot.child("name").getValue().toString());
//                email.setText(snapshot.child("email").getValue().toString());
//                password.setText(snapshot.child("password").getValue().toString());
                title.setText(m.getName()+"'s "+"flights");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        back.setOnClickListener(this);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button) {
            Intent myIntent = new Intent(getApplicationContext(), manager_activity.class);//move to main menu actiivity
            myIntent.putExtra("manager_id", manager_id);
            startActivity(myIntent);
        }


    }
}
