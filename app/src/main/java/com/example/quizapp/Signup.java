package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    public EditText e_mail;
    public EditText pass_word;
    public EditText confirm_Password;
    public Button sign_up;
    public Context context;
    public TextView login;
    public Toast toast;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        e_mail = findViewById(R.id.email_signup);
        pass_word = findViewById(R.id.password_signup);
        confirm_Password = findViewById(R.id.confirmPassword_signup);
        sign_up = findViewById(R.id.Signup);
        login = findViewById(R.id.wantToLogin);



        firebaseAuth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void signup(){

        String Email = e_mail.getText().toString();
        String Password = pass_word.getText().toString();
        String ConfirmPassword = confirm_Password.getText().toString();

        if(Email.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty()){
            toast = Toast.makeText(this,"Fields are empty", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(!Password.equals(ConfirmPassword)){
            toast = Toast.makeText(this,"Password and Confirm Password doesn't match", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{

            firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        // means authentication is successfull
                        Toast.makeText(getApplicationContext(),"Signup Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Signup Unsuccessful", Toast.LENGTH_SHORT).show();
                        Log.d("mohsin", task.getException().getMessage());
                    }
                }
            });
        }

    }
}