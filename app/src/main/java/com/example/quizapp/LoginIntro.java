package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class LoginIntro extends AppCompatActivity {

    Button getStarted;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro);

        getStarted = findViewById(R.id.getStarted);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if(user != null){
            Toast.makeText(getApplicationContext(),"User is already Logged in", Toast.LENGTH_SHORT).show();
            try {
                redirect("Main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    redirect("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void redirect(String name) throws IOException {

        if(name.equals("Login")){
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
        }

        else if(name.equals("Main")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        else{
            try {

            }
            catch(Exception e){
                e.printStackTrace();
                return;
            }


        }

        finish();
    }
}