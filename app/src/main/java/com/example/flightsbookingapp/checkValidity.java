package com.example.flightsbookingapp;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Pattern;

public class checkValidity {


    public static boolean check_email(EditText email) {
        if (TextUtils.isEmpty(email.getText().toString().trim())) //check if the input exist
        {
            email.setError("email is required!");
            return true;
        }
        if (!(emailValidity(email.getText().toString().trim())) && email.length()>0) //check validity of the input
        {
            email.setError("email is invalid!");
            return true;
        }
        return false;
    }

    public static boolean check_password(EditText password) {
        if (TextUtils.isEmpty(password.getText().toString().trim())) //check if the input exist
        {
            password.setError("password is required!");
            return true;
        }
        if (password.getText().toString().trim().length()<6) //check vaility of password
        {
            password.setError("password must be at least 6 characters");
            return true;
        }
        return false;
    }


    public static boolean check_name(EditText name_edit) {
        if (TextUtils.isEmpty(name_edit.getText().toString().trim()))
        {
            name_edit.setError("name is required!");
            return true;
        }
        if(name_edit.length()==1||!(nameValidity(name_edit.getText().toString().trim())))
        {
            name_edit.setError("invalid name");
            return true;
        }
        return false;
    }



    //check validity of an email
    private static boolean emailValidity(String email)
    {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    //check validity of an name
    private static boolean nameValidity(String s) {
        for(int i=0;i<s.length();i++)
        {
            if(! Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", s))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean check_confirm_pass(EditText password_edit, EditText confirm_text) {

        if (TextUtils.isEmpty(confirm_text.getText().toString().trim()))
        {
            confirm_text.setError("confirm your password!");
            return true;
        }
        if (!(password_edit.getText().toString().trim().equals(confirm_text.getText().toString().trim())))
        {
            confirm_text.setError("password and confirm password should match!");
            return true;
        }
        return false;
    }
}
