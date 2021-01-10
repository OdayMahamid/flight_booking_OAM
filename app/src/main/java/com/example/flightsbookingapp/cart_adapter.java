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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class cart_adapter extends FirebaseRecyclerAdapter<flights, cart_adapter.myviewholder>
{
    ArrayList<flights> list;
    public cart_adapter(@NonNull FirebaseRecyclerOptions<flights> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull flights flight_model)
    {
       holder.from_text.setText(flight_model.getFrom());
       holder.dest_text.setText(flight_model.getTo());
       holder.date_text.setText(flight_model.getDate());
       holder.cost_text.setText(flight_model.getPrice());
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_singlerow,parent,false);
       return new myviewholder(view);
    }


    static class myviewholder extends RecyclerView.ViewHolder
    {
        FirebaseAuth ref=FirebaseAuth.getInstance();
        TextView cost_text, from_text, dest_text, date_text, add;
        Button remove_button;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            cost_text = itemView.findViewById(R.id.user_email);
            from_text = itemView.findViewById(R.id.user_name_text);
            dest_text = itemView.findViewById(R.id.user_password_text);
            date_text = itemView.findViewById(R.id.date_text);
            remove_button = itemView.findViewById(R.id.remove_button);
            remove_button.setOnClickListener(c -> {
                FirebaseUser s = ref.getCurrentUser();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query cart_flight = ref.child("users").child(s.getUid()).child("user_cart").child(dest_text.getText().toString());
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

    }
}
