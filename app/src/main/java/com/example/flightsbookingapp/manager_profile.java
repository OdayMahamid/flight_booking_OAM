package com.example.flightsbookingapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class manager_profile extends AppCompatActivity implements View.OnClickListener{
    String manager_id;
    private TextView name;
    private TextView password;
    private TextView email;
    private Button change_name;
    private Button change_pass;
    Button dialog_name;
    EditText dialogname;
    EditText dialogpass;
    EditText dialogconfirm;
    Button dialog_pass;
    Dialog d,d1;
    ImageButton back;
    FirebaseDatabase db;
    DatabaseReference manageref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerprofile);
        back=findViewById(R.id.back_button);
        name=findViewById(R.id.nametext);
        password=findViewById(R.id.passtext);
        email=findViewById(R.id.email_text);
        manager_id=getIntent().getStringExtra("manager_id");
        change_name=findViewById(R.id.namechange);
        change_pass=findViewById(R.id.passwordchange);

        db=FirebaseDatabase.getInstance();
        manageref=db.getReference("Managers").child(manager_id);
        manageref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             Manager m = snapshot.getValue(Manager.class);
//                name.setText(snapshot.child("name").getValue().toString());
//                email.setText(snapshot.child("email").getValue().toString());
//                password.setText(snapshot.child("password").getValue().toString());
                name.setText(m.getName());
                email.setText(m.getEmail());
                password.setText(m.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        change_pass.setOnClickListener(this);
        change_name.setOnClickListener(this);


    }
    public  void changename()
    {
        d=new Dialog(this);
        d.setContentView(R.layout.layout_custom);
        d.setTitle("change your name");
        d.setCancelable(true);
        dialogname=d.findViewById(R.id.dialognametext);
        dialog_name=d.findViewById(R.id.change_button);
        dialog_name.setOnClickListener(this);
        d.show();

    }
    public void changepassword()
    {
        d1=new Dialog(this);
        d1.setContentView(R.layout.change_password);
        d1.setTitle("change your password");
        d1.setCancelable(true);
        dialogpass=d1.findViewById(R.id.newpass);
        dialogconfirm=d1.findViewById(R.id.confirmpass);
        dialog_pass=d1.findViewById(R.id.pass_change_b);
        dialog_pass.setOnClickListener(this);
        d1.show();


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.namechange)
        {
            changename();

        }
        if(v.getId() == R.id.passwordchange)
        {
            changepassword();
        }
        if(v.getId() == R.id.change_button)
        {
            String newname=dialogname.getText().toString();
            if(newname!=null)
            {
                manageref.child("name").setValue(newname);
                Toast.makeText(this,"user name changed ",Toast.LENGTH_LONG).show();
                d.dismiss();

            }
        }
        if(v.getId() == R.id.pass_change_b)
        {
            String pass=dialogpass.getText().toString().trim();
            String conf=dialogconfirm.getText().toString().trim();
            if(pass.equals(conf))
            {
                manageref.child("password").setValue(pass);
                System.out.println(pass);
                System.out.println(conf);
                Toast.makeText(this,"user password changed ",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this,"password and confirm password should match! ",Toast.LENGTH_LONG).show();

            d1.dismiss();
        }

    }
}
