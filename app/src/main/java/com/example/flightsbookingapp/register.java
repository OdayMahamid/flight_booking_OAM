package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    Toolbar toolbar;
    Button register_button;
    TextView login;
    EditText name_edit, email_edit, password_edit, confirm_text;
    ProgressBar prog;
    FirebaseAuth ref=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //
        name_edit = findViewById(R.id.signup_firstname);
        email_edit = findViewById(R.id.signup_email);
        password_edit = findViewById(R.id.signup_password);
        confirm_text = findViewById(R.id.signup_repassword);
        register_button = findViewById(R.id.signup_nextstep);
        login = findViewById(R.id.signup_loginBtn);

        // when user clicks on register button
        register_button.setOnClickListener(view -> {
                // checking validity of entered email, password, confirm password and name
                if (checkValidity.check_email(email_edit)
                        || checkValidity.check_password(password_edit) ||
                        checkValidity.check_confirm_pass(password_edit, confirm_text) ||
                        checkValidity.check_name(name_edit)) return;

                register_fB();
        });

        // when user clicks on already have account button
        login.setOnClickListener(view -> {
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(myIntent);
        });

    }


    /**
     *     connect to database and save user register information in it.
      */
    private void register_fB() {
        String nameText = name_edit.getText().toString().trim();
        String emailText = email_edit.getText().toString().trim();
        String passwordText = password_edit.getText().toString().trim();
        String confirmText = confirm_text.getText().toString().trim();
        prog = (ProgressBar) findViewById(R.id.signup_prog);

        prog.setVisibility(View.VISIBLE);
        //connection to db and create new user
        ref.fetchSignInMethodsForEmail(emailText).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) //check if email already exist
            {
                if(!task.getResult().getSignInMethods().isEmpty())
                {
                    email_edit.setError("email is already exist");
                    prog.setVisibility(View.GONE);
                    return;
                }
            }
        });
        ref.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) //checks if the connection was successful
            {
                if (task.isSuccessful()) {
                    User u = new User(nameText, ref.getCurrentUser().getEmail()); //create a User's object
                    DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("users");
                    ref1.child(ref.getCurrentUser().getUid()).setValue(u);
                    prog.setVisibility(View.GONE);
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class); //move to main menu actiivity
                    startActivity(myIntent);
                } else {
                    Toast.makeText(register.this, "Error!", Toast.LENGTH_SHORT).show();
                    prog.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }
}
