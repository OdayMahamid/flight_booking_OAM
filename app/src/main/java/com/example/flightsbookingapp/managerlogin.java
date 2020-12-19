package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class managerlogin extends AppCompatActivity {

    private EditText email_editText;
    private EditText password_editText;
    private Button login_button;
    private TextView signUp_text, forgotPassword_text;
    FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
    DatabaseReference reference;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerlogin);
        email_editText = findViewById(R.id.signin_email);
        password_editText = findViewById(R.id.signin_password);
        login_button = findViewById(R.id.cust_button);
        signUp_text = findViewById(R.id.signin_signupBtn);
        forgotPassword_text = findViewById(R.id.signin_forgotpasswod);
        reference=rootNode.getReference("Managers");

        //login to the app with email and password
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= email_editText.getText().toString();
                String pass= password_editText.getText().toString();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if (snapshot.child(email).exists() && snapshot.child(pass).exists())
                       {
                           Toast.makeText(managerlogin.this, "your login was success", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(managerlogin.this, manageractivity.class);
                           intent.putExtra("email", email);
                           startActivity(intent);
                       }
                       else
                           Toast.makeText(getApplicationContext(),"ERROR: invalid email or password",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }


    });

        //sign up activity
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), manager_register.class);
                startActivity(myIntent);
            }
        });

                forgotPassword_text.setOnClickListener(view -> {
                    Intent myIntent = new Intent(getApplicationContext(), register.class);
                    startActivity(myIntent);
                });

    }


    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }




}