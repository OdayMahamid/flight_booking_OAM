package com.example.flightsbookingapp;

import android.content.Context;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.node.NullNode;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.NullValue;


import java.util.ListIterator;
import java.util.Objects;


public class   searchadapter extends RecyclerView.Adapter<searchadapter.myviewholder> {

    List<flights> sky = new LinkedList<>();
    String FH;
    boolean flag = false;
    private LayoutInflater mInflater;


    public searchadapter() {


        Query mQueryRef = FirebaseDatabase.getInstance().getReference().child("flights");


        mQueryRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    flights f = postSnapshot.getValue(flights.class);
                    Log.d("sky reg", "onDataChange: "+sky.size());
                    sky.add(postSnapshot.getValue(flights.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }

    public searchadapter(String From) {
        sky.clear();

        this.FH = From;


        Query mQueryRef = FirebaseDatabase.getInstance().getReference().child("flights");


        mQueryRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Log.d("sky reg2", "onDataChange: "+sky.size());
                    flights f = postSnapshot.getValue(flights.class);
                    Log.d("sky reg2", "myadapter:  " + f.getFrom());
                    if (f.getFrom().equals(From)) sky.add(postSnapshot.getValue(flights.class));


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Log.d("size", "myadapter:  "+ String.valueOf(sky.size()));
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
            cost_text = itemView.findViewById(R.id.cost_text);
            from_text = itemView.findViewById(R.id.from_text);
            dest_text = itemView.findViewById(R.id.dest_text);
            date_text = itemView.findViewById(R.id.date_text);
            b = itemView.findViewById(R.id.button232);

            // buy button
            b.setOnClickListener(v -> {
                flights u = new flights(from_text.getText().toString(), dest_text.getText().toString(), date_text.getText().toString(),cost_text.getText().toString());
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("users/"+ Objects.requireNonNull(ref.getCurrentUser()).getUid()+"/user_flights/"+u.getTo());
                ref1.child(ref.getCurrentUser().getUid()).setValue(u);

            });
        }


    }
}

