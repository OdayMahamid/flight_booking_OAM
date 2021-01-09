package com.example.flightsbookingapp;
import java.lang.Object;
import java.util.LinkedList;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import	android.widget.BaseAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class booking  extends AppCompatActivity  {
    private static final String[] countries = new String[]{"China", "Italy", "Armenia", "israel", "England", "Germany", "Algeria","Paris","dubai"};
    RecyclerView recView;
    DatabaseReference databaseReference;
    myadapter adapter;
    searchadapter myad;
    ImageView pic ;
    AutoCompleteTextView search;

   // Query mQueryRef = FirebaseDatabase.getInstance().getReference().child("flights");

   // private ItemClickListener mClickListener;
    private FirebaseAuth ref = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
         search = findViewById(R.id.search);
         pic = findViewById(R.id.img);
      //  List<flights> sky=new LinkedList<flights>();
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("flights");
        FirebaseRecyclerOptions<flights> options =
                new  FirebaseRecyclerOptions.Builder<flights>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("flights"), flights.class)
                        .build();
        adapter=new myadapter(options);
        recView.setAdapter(adapter);




        //recView.refreshDrawableState();
        //Log.d("sky reg", "onCreate: "+String.valueOf(recView.getChildCount()));
        // recView.notifyAll();
     //   Log.d("hello", "deccount" +String.valueOf(recView.getScrollBarSize()) + "child count: " + String.valueOf(0));
        //final String[] sour = new String[1];
        /*
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);
        search.setThreshold(1);
                search.setAdapter(adap);

*/
        FirebaseUser s = ref.getCurrentUser();




// Do something in response to button click
        final String[] sour = new String[1];

        pic.setOnClickListener(v -> {
            sour[0] = search.getText().toString();
            Log.d("picl", sour[0]);
            myad=new searchadapter(sour[0]);

          recView.setAdapter(myad);



});








}


    @Override
    protected void onStart() {

        super.onStart();
       adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    }
