package com.example.flightsbookingapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class   myadapter extends FirebaseRecyclerAdapter<flights,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<flights> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull flights flight_model)
    {
       holder.from_text.setText("From: " + flight_model.from);
       holder.dest_text.setText("To: " + flight_model.dest);
       holder.todate_text.setText("To: " + flight_model.todate);
        holder.fromdate_text.setText("From: " + flight_model.fromdate);
        holder.cost_text.setText("Cost: " + flight_model.cost);
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view);
    }


    static class myviewholder extends RecyclerView.ViewHolder
    {
        FirebaseAuth ref=FirebaseAuth.getInstance();
        TextView cost_text, from_text, dest_text, fromdate_text, todate_text;
        Button b;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            cost_text = itemView.findViewById(R.id.cost_text);
            from_text = itemView.findViewById(R.id.from_text);
            dest_text = itemView.findViewById(R.id.dest_text);
            fromdate_text = itemView.findViewById(R.id.fromdate_text);
            todate_text = itemView.findViewById(R.id.todate_text);
            b = itemView.findViewById(R.id.button232);

            // buy button
            b.setOnClickListener(v -> {
                flights u = new flights(from_text.getText().toString(), dest_text.getText().toString(), fromdate_text.getText().toString(), todate_text.getText().toString(),cost_text.getText().toString());
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("users/"+ Objects.requireNonNull(ref.getCurrentUser()).getUid()+"/user_flights/"+u.dest);
                ref1.child(ref.getCurrentUser().getUid()).setValue(u);
            });
        }

    }
}
