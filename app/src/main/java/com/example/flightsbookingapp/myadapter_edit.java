package com.example.flightsbookingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;


public class myadapter_edit extends FirebaseRecyclerAdapter<flights, myadapter_edit.myviewholder>
{

    public myadapter_edit(@NonNull FirebaseRecyclerOptions<flights> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull flights flight_model)
    {
       holder.from_text.setText(flight_model.getFrom());
       holder.dest_text.setText(flight_model.getTo());
       holder.date_land_text.setText(flight_model.lan_time);
       holder.edit_dep_time.setText(flight_model.dep_time);
       holder.cost_text.setText(flight_model.getPrice());
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
       return new myviewholder(view,parent.getContext());
    }


    static class myviewholder extends RecyclerView.ViewHolder
    {
        FirebaseAuth ref=FirebaseAuth.getInstance();
        EditText cost_text, from_text, dest_text, date_land_text, edit_dep_time;
        Button b;
        private Context context;


        public myviewholder(@NonNull View itemView, Context context)
        {
            super(itemView);
            this.context = context;
            cost_text = itemView.findViewById(R.id.edit_price);
            from_text = itemView.findViewById(R.id.edit_from);
            dest_text = itemView.findViewById(R.id.edit_to);
            date_land_text = itemView.findViewById(R.id.edit_land_time);
            edit_dep_time = itemView.findViewById(R.id.edit_dep_time);
            b = itemView.findViewById(R.id.cust_button);

            // edit button
            b.setOnClickListener(this::onClick);
        }

        private void onClick(View v) {
            Intent intent = new Intent(context, mang_edit_flight.class);
//            intent.putExtra("manager_id", manager_id);
            context.startActivity(intent);
        }
    }
}
