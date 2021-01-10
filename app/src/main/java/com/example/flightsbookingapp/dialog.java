package com.example.flightsbookingapp;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class dialog extends AppCompatDialogFragment {
    String manager_id;
    private EditText name;
    private EditText password;
    private TextView email;
    private Button save_button;
    FirebaseDatabase db;
    DatabaseReference manage_ref;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_managerprofile, null);
        builder.setView(view)
                .setTitle("edit profile")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseUser u = auth.getCurrentUser();
                        String nameText = name.getText().toString().trim();
                        String passText = password.getText().toString().trim();
                        manage_ref = db.getReference("Managers").child(manager_id);
                        manage_ref = manage_ref.getRef().child("name");
                        manage_ref.setValue(nameText);
                        manage_ref = db.getReference("Managers").child(manager_id);
                        manage_ref = manage_ref.getRef().child("password");
                        manage_ref.setValue(passText);
                        listener.applyTexts(name.getText().toString(), password.getText().toString());
                        Toast.makeText(getContext(), "profile changed successfully", Toast.LENGTH_LONG).show();

                    }
                });

        name = view.findViewById(R.id.name_text);
        password = view.findViewById(R.id.password_text);
        manager_id = getActivity().getIntent().getStringExtra("manager_id");
        db = FirebaseDatabase.getInstance();
        manage_ref = db.getReference("Managers").child(manager_id);
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface ExampleDialogListener {
        void applyTexts(String username, String password);
    }
}