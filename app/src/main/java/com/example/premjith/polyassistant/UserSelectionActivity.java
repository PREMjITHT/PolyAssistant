package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserSelectionActivity extends AppCompatActivity {
Button btnAuth,btnGuest;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
       // Toolbar toolbar =findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        btnAuth=findViewById(R.id.btn_user_phoneAuth);
        btnGuest=findViewById(R.id.btn_user_guest);

        findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We normally won't show the welcome slider again in real app
                // but this is for testing
                PrefManager prefManager = new PrefManager(getApplicationContext());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(UserSelectionActivity.this, WelcomeActivity.class));
                finish();
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSelectionActivity.this, "Under Construction...!", Toast.LENGTH_SHORT).show();
            }
        });


        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii=new Intent(getApplicationContext(),PhoneAuthActivity.class);
                startActivity(ii);


            }
        });



    }



}
