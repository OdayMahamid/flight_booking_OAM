package com.example.flightsbookingapp;

import android.content.Context;
import android.content.Intent;
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


public class user_adapter extends FirebaseRecyclerAdapter<User, user_adapter.myviewholder>
{
    public user_adapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull User user_model)
    {
       holder.from_text.setText("Email: " +  user_model.email);
       holder.dest_text.setText("Name: " +  user_model.name);
       holder.cost_text.setText("created date: " +  user_model.created_date);
//       holder.pass_text.setText(user_model.password);
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_user,parent,false);
       return new myviewholder(view, parent.getContext());
    }


    static class myviewholder extends RecyclerView.ViewHolder
    {
        FirebaseAuth ref=FirebaseAuth.getInstance();
        TextView cost_text, from_text, dest_text,pass_text;
        Button b;
        private Context context;
        public myviewholder(@NonNull View itemView, Context context)
        {
            super(itemView);
            this.context = context;
            cost_text = itemView.findViewById(R.id.user_email);
            from_text = itemView.findViewById(R.id.user_name_text);
            dest_text = itemView.findViewById(R.id.user_password_text);
            b = itemView.findViewById(R.id.cust_button);

            // customer flights button
            b.setOnClickListener(v -> {
                Intent intent = new Intent(context, Activity_cus_flights.class);
           intent.putExtra("user_email", dest_text.getText().toString());
//                intent.putExtra("user_password", pass_text.getText().toString());
                context.startActivity(intent);
            });
        }

    }
}
