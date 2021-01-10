package com.example.flightsbookingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class inter_receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null)
        {
            if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE)
            {
//                Toast.makeText(context,"You are now connected to Mobile Data",Toast.LENGTH_SHORT).show();
            }
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI)
            {
//                Toast.makeText(context,"You are now connected to WIFI",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Intent myIntent = new Intent(context, activity_lost_connection.class);
            context.startActivity(myIntent);
        }
    }
}