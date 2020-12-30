package com.example.flightsbookingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class manager_profile extends AppCompatActivity implements View.OnClickListener{
    String manager_id;
    private EditText name;
    private EditText password;
    private TextView email;
    ImageButton back;
    FirebaseDatabase db;
    DatabaseReference manageref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerprofile);
        back=findViewById(R.id.back_button);
        name=findViewById(R.id.name_text);
        password=findViewById(R.id.password_text);
        email=findViewById(R.id.email_text);
        manager_id=getIntent().getStringExtra("manager_id");

        db=FirebaseDatabase.getInstance();
        manageref=db.getReference("Managers").child(manager_id);
        manageref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             Manager m = snapshot.getValue(Manager.class);

//                name.setText(snapshot.child("name").getValue().toString());
//                email.setText(snapshot.child("email").getValue().toString());
//                password.setText(snapshot.child("password").getValue().toString());
                name.setText(m.getName());
                email.setText(m.getEmail());
                password.setText(m.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
