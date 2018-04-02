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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Thread.sleep;

public class UserSelectionActivity extends AppCompatActivity {
Button btnAdmin,btnGuest,btnSuperAdmin;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
     Toolbar mToolbar;
     String  temp;
     String p;
    View b2;
     LinearLayout linearLayout;
    ProgressBar pgsBar,pgsHori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        // Toolbar toolbar =findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        pgsHori=findViewById(R.id.pBar3_user);
          b2 = pgsHori;
       // pgsBar=findViewById(R.id.pBar_user);
        btnAdmin = findViewById(R.id.btn_user_admin);
        btnGuest = findViewById(R.id.btn_user_guest);
        btnSuperAdmin=findViewById(R.id.btn_user_super_admin);
        linearLayout= findViewById(R.id.line_snackbar);
        mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        b2.setVisibility(View.GONE);

        //View bSuperAdmin = btnSuperAdmin;
       // bSuperAdmin.setVisibility(View.VISIBLE);
        if (isNetworkAvailable()){


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
                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setFirstTimeLaunch(true);
                startActivity(new Intent(UserSelectionActivity.this, WelcomeActivity.class));
                finish();
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setVisibility(View.VISIBLE);
                Intent in=new Intent(getApplicationContext(),StudentSearch.class);
                startActivity(in);

            }
        });


        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View b = pgsBar;
                //b.setVisibility(View.VISIBLE);

                b2.setVisibility(View.VISIBLE);
                String u="admin";
                Intent i = new Intent(getApplicationContext(), PhoneAuthActivity.class);
                i.putExtra("ad",1);
                startActivity(i);


            }
        });
        btnSuperAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setVisibility(View.VISIBLE);
                String u="superadmin";
                Intent i = new Intent(getApplicationContext(),PhoneAuthActivity.class);
                i.putExtra("ad",2);
                startActivity(i);
            }
        });


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();

        b2.setVisibility(View.GONE);
    }
}
