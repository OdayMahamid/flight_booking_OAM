package com.example.flightsbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class activity_customer_cart extends AppCompatActivity {

    RecyclerView recView;
    DatabaseReference databaseReference;
    cart_adapter adapter;
    FirebaseRecyclerOptions<flights> options;
    Button buy_button;
    private FirebaseAuth ref = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);
        buy_button = findViewById(R.id.buyAll_button);
        FirebaseUser s = ref.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        recView = findViewById(R.id.shopping_recV);
        recView.setLayoutManager(new LinearLayoutManager(this));
        options =
                new  FirebaseRecyclerOptions.Builder<flights>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").child(s.getUid()).child("user_cart"), flights.class)
                        .build();
        adapter=new cart_adapter(options);
        recView.setAdapter(adapter);
        buy_button.setOnClickListener(task ->{
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

            Query cart_flight = ref.child("users").child(s.getUid()).child("user_cart");
            cart_flight.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        DatabaseReference child = ref.child("users").child(s.getUid()).child("bought flights").child(""+ appleSnapshot.hashCode());
                        child.setValue(appleSnapshot.getValue());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            cart_flight.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

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