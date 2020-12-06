package com.example.flightsbookingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myadapter extends FirebaseRecyclerAdapter<flights,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<flights> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull flights flight_model)
    {
       holder.from_text.setText(flight_model.from);
       holder.dest_text.setText(flight_model.dest);
       holder.todate_text.setText(flight_model.todate);
        holder.fromdate_text.setText(flight_model.fromdate);
        holder.cost_text.setText(flight_model.cost);
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
        TextView cost_text, from_text, dest_text, fromdate_text, todate_text;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            cost_text = itemView.findViewById(R.id.cost_text);
            from_text = itemView.findViewById(R.id.from_text);
            dest_text = itemView.findViewById(R.id.dest_text);
            fromdate_text = itemView.findViewById(R.id.fromdate_text);
            todate_text = itemView.findViewById(R.id.todate_text);
        }
    }
}
