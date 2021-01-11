package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class custlogin extends AppCompatActivity {

    private EditText email_editText;
    private EditText password_editText;
    private Button login_button, signUp_text;
    private TextView  forgotPassword_text;
    private FirebaseAuth ref = FirebaseAuth.getInstance();
    String cust_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custlogin);
        email_editText = findViewById(R.id.signin_email);
        password_editText = findViewById(R.id.signin_password);
        login_button = findViewById(R.id.login_button);
        signUp_text = findViewById(R.id.signin_signupBtn);
        forgotPassword_text = findViewById(R.id.signin_forgotpasswod);


        //login to the app with email and password
        login_button.setOnClickListener(view -> {
            String emailText = email_editText.getText().toString().trim();
            String passwordText = password_editText.getText().toString().trim();

            // checking validity of entered email and password
            if(checkValidity.check_email(email_editText)
                    || checkValidity.check_password(password_editText)) return;

            //connect to firebase and try to login
            signIn_fB(emailText, passwordText);

        });

        //sign up activity
        signUp_text.setOnClickListener(view -> {
            Intent myIntent = new Intent(getApplicationContext(), register.class);
            startActivity(myIntent);
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


    /// private function
    private void signIn_fB(String emailText, String passwordText) {
        ref.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(task -> {
            if (task.isSuccessful()) //check if the connection was successful
            {

                Intent myIntent = new Intent(getApplicationContext(), booking.class);
                startActivity(myIntent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"ERROR: invalid email or password",Toast.LENGTH_LONG).show();
            }
        });
    }

}