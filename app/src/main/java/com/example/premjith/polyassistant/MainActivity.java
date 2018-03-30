package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    android.support.v7.widget.Toolbar toolbar=null;

    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button mSignOutButton =  findViewById(R.id.sign_out_button);
        TextView fireBaseId =  findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }
        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.sign_out_button:

                        Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        startActivity(new Intent(MainActivity.this, PhoneAuthActivity.class));
                        finish();
                        break;
                }







            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Button buttonCT=findViewById(R.id.btn_branch_Computer);
        Button buttonMECH=findViewById(R.id.btn_branch_mech);
        Button buttonCIVIL=findViewById(R.id.btn_branch_Civil);
        Button buttonELECTRICAL=findViewById(R.id.btn_branch_electrical);
        Button buttonELECTRONICS=findViewById(R.id.btn_branch_electronics);
        buttonCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Computer", Toast.LENGTH_SHORT).show();
                Intent Inte=new Intent(MainActivity.this,SelectSemester.class);
                Inte.putExtra("key",1);
                startActivity(Inte);

            }
        });

        buttonCIVIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this, "Civil", Toast.LENGTH_SHORT).show();
                Intent Inte=new Intent(MainActivity.this,SelectSemester.class);
                Inte.putExtra("key",2);
                startActivity(Inte);


            }
        });

        buttonMECH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Mechanical", Toast.LENGTH_SHORT).show();
                Intent Inte=new Intent(MainActivity.this,SelectSemester.class);
                Inte.putExtra("key",3);
                startActivity(Inte);
            }
        });

        buttonELECTRONICS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Electronics", Toast.LENGTH_SHORT).show();
                Intent Inte=new Intent(MainActivity.this,SelectSemester.class);
                Inte.putExtra("key",5);
                startActivity(Inte);
            }
        });



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();

        switch (id){


            case R.id.nav_Profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
               Intent i1= new Intent(getApplicationContext(),profilee.class);
                startActivity(i1);
                break;
            /*case R.id.nav_Branches:
                Toast.makeText(this, "Branches", Toast.LENGTH_SHORT).show();
                Intent i2= new Intent(MainActivity.this,MainActivity.class);
                i2.putExtra("key",2);
                startActivity(i2);
                break;
            case R.id.nav_Semester:
                Toast.makeText(this, "Semester", Toast.LENGTH_SHORT).show();
                Intent i3= new Intent(Navigation.this,SelectSemester.class);
                i3.putExtra("key",3);
                startActivity(i3);
                break;
            case R.id.nav_Result:
                Toast.makeText(this, "Result", Toast.LENGTH_SHORT).show();
                Intent i4= new Intent(Navigation.this,Result.class);
                i4.putExtra("key",4);
                startActivity(i4);
               break;*/
            case R.id.nav_About:
                Toast.makeText(this, "Version 1.0 beta", Toast.LENGTH_SHORT).show();
                Intent i5= new Intent(getApplicationContext(),About.class);

                startActivity(i5);
                break;
            case R.id.nav_Settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                //Intent i7= new Intent(MainActivity.this,.class);
                // i7.putExtra("key",7);
                // startActivity(i7);
                break;
            case R.id.nav_Logout:
                Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, PhoneAuthActivity.class));
                finish();
                break;
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }









}
