package com.example.flightsbookingapp;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User
{
    public String name;
    public String email;
    public String created_date;


    // basic constructors
    public User() {

    }

    public User(String name,String email) {
        this.name=name;
        this.email=email;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.created_date = simpleDateFormat.format(new Date());
    }


}
