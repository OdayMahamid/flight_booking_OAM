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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class manager_register extends AppCompatActivity {
    Button register_button;
    TextView login;
    EditText name_edit, email_edit, password_edit, confirm_text,managerpass;
    FirebaseAuth ref=FirebaseAuth.getInstance();
    FirebaseDatabase rootNode=FirebaseDatabase.getInstance();
    DatabaseReference reference;
    Manager m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerregister);

        name_edit = findViewById(R.id.signup_firstname);
        email_edit = findViewById(R.id.signup_email);
        password_edit = findViewById(R.id.signup_password);
        confirm_text = findViewById(R.id.signup_repassword);
        register_button = findViewById(R.id.signup_nextstep);
        login = findViewById(R.id.signup_loginBtn);
        managerpass=findViewById(R.id.managerpass);
        reference=rootNode.getReference("Managers");


        // when user clicks on register button
        register_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String manager= managerpass.getText().toString();
                String nameText = name_edit.getText().toString().trim();
                String emailText = email_edit.getText().toString().trim();
                String passwordText = password_edit.getText().toString().trim();
                String confirmText = confirm_text.getText().toString().trim();
                if(!manager.equals("manager1234"))
                {
                    Toast.makeText(getApplicationContext(),"ERROR: invalid manager password",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_LONG).show();
                }
                else {
                    if (passwordText.equals(confirmText)) {
                        m = new Manager(nameText, emailText, passwordText);
                        ref.fetchSignInMethodsForEmail(emailText).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) //check if email already exist
                            {
                                if (!task.getResult().getSignInMethods().isEmpty()) {
                                    email_edit.setError("email is already exist");
                                }
                            }
                        });
                        ref.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) //checks if the connection was successful
                            {
                                if (task.isSuccessful()) {
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Managers");
                                    ref1.child(ref.getCurrentUser().getUid()).setValue(m);
                                    Intent myIntent = new Intent(getApplicationContext(), managerlogin.class); //move to main menu actiivity
                                    startActivity(myIntent);
                                } else {
                                    Toast.makeText(manager_register.this, "Error!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else
                        Toast.makeText(manager_register.this, "The password does not match!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // when user clicks on already have account button
        login.setOnClickListener(view -> {
            Intent myIntent = new Intent(getApplicationContext(), managerlogin.class);
            startActivity(myIntent);
        });

    }



    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(getApplicationContext(), managerlogin.class);
        startActivity(myIntent);
    }
}
