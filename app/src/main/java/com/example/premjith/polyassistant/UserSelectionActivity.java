package com.example.premjith.polyassistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserSelectionActivity extends AppCompatActivity {
Button btnAuth,btnGuest;

     Toolbar mToolbar;
     LinearLayout linearLayout;
     FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        // Toolbar toolbar =findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        btnAuth = findViewById(R.id.btn_user_phoneAuth);
        btnGuest = findViewById(R.id.btn_user_guest);
        linearLayout= findViewById(R.id.line_snackbar);
        mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (isNetworkAvailable()){

            Toast.makeText(this, "Online Mode", Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar.make(linearLayout, "Online Mode...!", Snackbar.LENGTH_LONG);
            // Changing message text color
            snackbar.setActionTextColor(Color.RED);

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.GREEN);

            snackbar.show();
            {


            }
        }else {
            Toast.makeText(this, "Offline Mode", Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar.make(linearLayout, "Offline Mode...!", Snackbar.LENGTH_LONG);
            // Changing message text color
            snackbar.setActionTextColor(Color.RED);

            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);

            snackbar.show();
            {


            }
        }




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

                Intent ii = new Intent(getApplicationContext(), PhoneAuthActivity.class);
                startActivity(ii);


            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
