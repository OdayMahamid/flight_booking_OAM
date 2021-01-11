package com.example.flightsbookingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class flightsadabter extends FirebaseRecyclerAdapter<
        flights, flightsadabter.flightsViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public flightsadabter(@NonNull FirebaseRecyclerOptions<flights> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull flightsViewholder holder,
                     int position, @NonNull flights model)
    {

        // Add "from" from model class (here
        // "flights.class")to appropriate view in Card
        // view (here "manager_flight.xml")
        holder.from.setText(model.getFrom());

        // Add to from model class (here
        // "flights.class")to appropriate view in Card
        // view (here "manager_flights.xml")
        holder.to.setText(model.getTo());

        // Add date from model class (here
        // "flights.class")to appropriate view in Card
        // view (here "manager_flights.xml")
        holder.date.setText(model.getDate());
        holder.price.setText(model.getPrice());
        String timestr =model.getDep_time()
                +"-"
                +model.getLan_time();

        holder.time.setText(timestr);

    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public flightsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manager_flights, parent, false);
        return new flightsViewholder(view);
    }



    // Sub Class to create references of the views in Crad
    // view (here "manager_flights.xml")
    class flightsViewholder
            extends RecyclerView.ViewHolder {
        TextView from, to, date, time , price;
        public flightsViewholder(@NonNull View itemView)
        {
            super(itemView);

            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            date = itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.timetext);
            price=itemView.findViewById(R.id.price);
        }
    }
}
