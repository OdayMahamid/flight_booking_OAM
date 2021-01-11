package com.example.flightsbookingapp;

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

import java.util.ArrayList;
import java.util.Objects;


public class   myadapter extends FirebaseRecyclerAdapter<flights,myadapter.myviewholder>
{
    ArrayList<flights> list;
    public myadapter(@NonNull FirebaseRecyclerOptions<flights> options) {
        super(options);
    }

    public myadapter(FirebaseRecyclerOptions<flights> options,ArrayList<flights> list) {
        super(options);
        this.list=list;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull flights flight_model)
    {
       holder.from_text.setText("From: " + flight_model.getFrom());
       holder.dest_text.setText("To: " + flight_model.getTo());
       holder.date_text.setText("Date: " + flight_model.getDate());
       holder.cost_text.setText("Cost: " + flight_model.getPrice());
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
        TextView cost_text, from_text, dest_text, date_text, add;
        Button b;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            cost_text = itemView.findViewById(R.id.user_email);
            from_text = itemView.findViewById(R.id.user_name_text);
            dest_text = itemView.findViewById(R.id.user_password_text);
            date_text = itemView.findViewById(R.id.date_text);
            add = itemView.findViewById(R.id.confirm_text);
            b = itemView.findViewById(R.id.cust_button);

            // buy button
            b.setOnClickListener(v -> {
                flights u = new flights(from_text.getText().toString(), dest_text.getText().toString(), date_text.getText().toString(),cost_text.getText().toString());
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("users/"+ Objects.requireNonNull(ref.getCurrentUser()).getUid()+"/user_cart/");
                ref1.child(u.getTo()).setValue(u);
                add.setVisibility(View.VISIBLE);
            });
        }

    }
}
