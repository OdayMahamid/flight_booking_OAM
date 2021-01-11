package com.example.flightsbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FlightDetails extends AppCompatActivity implements View.OnClickListener {
    private FirebaseDatabase db ;
    private DatabaseReference flightRef ;
    private EditText from;
    private EditText to;
     EditText dep_time;
    EditText land_time;
    private EditText day;
    private EditText month;
    private EditText year;
    private EditText price;
    private Button add;
    private  flights info;
    String manager_id;
    static boolean flag=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addflight);
        from = (EditText)findViewById(R.id.fromtext);
        to = (EditText)findViewById(R.id.totext);
        dep_time = (EditText)findViewById(R.id.deptime);
        land_time =(EditText) findViewById(R.id.landtime);
        day = (EditText) findViewById(R.id.daytext);
        month = (EditText) findViewById(R.id.monthtext);
        year =(EditText) findViewById(R.id.yeartext);
        price =(EditText) findViewById(R.id.pricetext);
        add = (Button)findViewById(R.id.addbutton);
        manager_id=getIntent().getStringExtra("manager_id");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearStr=year.getText().toString().trim();
                String monthStr=month.getText().toString().trim();
                String dayStr=day.getText().toString().trim();
                String dateStr=dayStr+"."+monthStr+"."+yearStr;
                String fromStr=from.getText().toString().trim();
                String toStr=to.getText().toString().trim();
                String priceStr=price.getText().toString().trim();
                String deptimestr=dep_time.getText().toString().trim();
                String landstr=land_time.getText().toString().trim();

                //a new flight object
                info =new flights(fromStr,toStr,dayStr,monthStr,yearStr,deptimestr,landstr,priceStr);

                db = FirebaseDatabase.getInstance();
                flightRef=db.getReference("flights");

                flightRef.addListenerForSingleValueEvent(new ValueEventListener()
                {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        for (DataSnapshot postSnapshot : snapshot.getChildren() )
                        {
                            //from DB
                            String fromflight = (String) postSnapshot.child("from").getValue().toString();
                            String toflight= (String) postSnapshot.child("to").getValue().toString();
                            String dateflight= (String) postSnapshot.child("date").getValue().toString();
                            String deptime=(String) postSnapshot.child("dep_time").toString();
                            String landtime=(String) postSnapshot.child("lan_time").toString();

                            // if this flight is already exist ?
                            if (fromflight.equals(fromStr)&&toflight.equals(toStr)&&dateflight.equals(dateStr)
                                && deptime.equals(deptimestr)&& landtime.equals(landstr))
                             {
                                Toast.makeText(FlightDetails.this, "this flight is already exist", Toast.LENGTH_SHORT).show();
                                flag=true;
                                break;
                            }
                        }
                    // if this is a new flight
                    if(flag==false)
                    {
                        info.setDay(null); info.setMonth(null); info.setYear(null);
                        String key=flightRef.push().getKey();
                        //push this flight to the DB
                        flightRef.child(key).setValue(info,completionListener);

                        info.setYear(yearStr); info.setDay(dayStr); info.setMonth(monthStr);

                        //add this flight to the manager
                        DatabaseReference manageref= db.getReference("Managers");
                        manageref.child(manager_id).child("flights").child(key).setValue(info);

                        Intent intent=new Intent(getApplicationContext(), manager_activity.class);
                        intent.putExtra("manager_id", manager_id);
                        startActivity(intent);

                    }
                }
                    DatabaseReference.CompletionListener completionListener = new
                            DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference)
                                {
                                    if (databaseError != null)
                                    {
                                        Toast.makeText(FlightDetails.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(FlightDetails.this,"Added!!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            };
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            }
        });
    }
    @Override
    public void onClick(View v) { }

}
