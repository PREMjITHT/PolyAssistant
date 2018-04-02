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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    android.support.v7.widget.Toolbar toolbar=null;
    ProgressBar pb;
    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;
    int x;
    View bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pb=findViewById(R.id.pBar3_main);
        bb = pb;
        bb.setVisibility(View.GONE);
        Button mSignOutButton =  findViewById(R.id.sign_out_button);
        TextView fireBaseId =  findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
//            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }

        x=getIntent().getExtras().getInt("k");












        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.sign_out_button:
                        bb.setVisibility(View.VISIBLE);

                        Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        startActivity(new Intent(MainActivity.this, UserSelectionActivity.class));
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
        Button buttonELECTRONICS=findViewById(R.id.btn_branch_electronics);
        buttonCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Computer", Toast.LENGTH_SHORT).show();
                Intent Inte=new Intent(MainActivity.this,Alert.class);
                Inte.putExtra("key",1);
                Inte.putExtra("ad",+x);
                startActivity(Inte);

            }
        });

        buttonCIVIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this, "Civil", Toast.LENGTH_SHORT).show();
                Intent Inte=new Intent(MainActivity.this,Alert.class);
                Inte.putExtra("key",2);
                Inte.putExtra("ad",+x);
                startActivity(Inte);


            }
        });

        buttonMECH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Comming soon...!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonELECTRONICS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Comming Soon...!", Toast.LENGTH_SHORT).show();
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
                finish();
                break;

            case R.id.nav_About:
                Toast.makeText(this, "Version 1.0 beta", Toast.LENGTH_SHORT).show();
                Intent i5= new Intent(getApplicationContext(),About.class);
                startActivity(i5);
                finish();
                break;
            case R.id.nav_Settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_Logout:
                bb.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, UserSelectionActivity.class));
                finish();
                break;
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        bb.setVisibility(View.GONE);
    }
}
