package com.example.flightsbookingapp;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;


public class search_adapter extends RecyclerView.Adapter<search_adapter.myviewholder> {

    List<flights> sky = new LinkedList<>();
    String FH;
    boolean flag = false;
    private LayoutInflater mInflater;


    public search_adapter() {


        Query mQueryRef = FirebaseDatabase.getInstance().getReference().child("flights");


        mQueryRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    flights f = postSnapshot.getValue(flights.class);
                    sky.add(postSnapshot.getValue(flights.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public search_adapter(String From) {
        sky.clear();
        this.FH = From;
        Query mQueryRef = FirebaseDatabase.getInstance().getReference().child("flights");
        mQueryRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    flights f = postSnapshot.getValue(flights.class);
                    if (f.getFrom().equals(From)) sky.add(postSnapshot.getValue(flights.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        if (!sky.isEmpty()) {
            holder.from_text.setText("From: " + sky.get(position).getFrom());
            holder.dest_text.setText("To: " + sky.get(position).getTo());
            holder.date_text.setText("To Date: " + sky.get(position).getDate());
            holder.cost_text.setText("Cost: " + sky.get(position).getPrice());
        }


    }

    @Override
    public int getItemCount() {
        return sky == null ? 0 : sky.size();
        // return sky.size();
    }


    static class myviewholder extends RecyclerView.ViewHolder  {
        FirebaseAuth ref=FirebaseAuth.getInstance();
        TextView cost_text, from_text, dest_text, date_text;
        Button b;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            cost_text = itemView.findViewById(R.id.user_email);
            from_text = itemView.findViewById(R.id.user_name_text);
            dest_text = itemView.findViewById(R.id.user_password_text);
            date_text = itemView.findViewById(R.id.date_text);
            b = itemView.findViewById(R.id.edit_flight_button);

            // buy button
            b.setOnClickListener(v -> {
                flights u = new flights(from_text.getText().toString(), dest_text.getText().toString(), date_text.getText().toString(),cost_text.getText().toString());
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("users/"+ Objects.requireNonNull(ref.getCurrentUser()).getUid()+"/user_flights/"+u.getTo());
                ref1.child(ref.getCurrentUser().getUid()).setValue(u);

            });
        }


    }
}