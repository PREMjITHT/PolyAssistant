package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class profilee extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //We dont need this.


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
                Intent i1= new Intent(profilee.this,profilee.class);
                i1.putExtra("key",1);
                startActivity(i1);
                break;
           /* case R.id.nav_Branches:
                Toast.makeText(this, "Branches", Toast.LENGTH_SHORT).show();
                Intent i2= new Intent(profilee.this,MainActivity.class);
                i2.putExtra("key",2);
                startActivity(i2);
                break;
            case R.id.nav_Semester:
                Toast.makeText(this, "Semester", Toast.LENGTH_SHORT).show();
               /* Intent i3= new Intent(Navigation.this,SelectSemester.class);
                i3.putExtra("key",3);
                startActivity(i3);
               */// break;
           // case R.id.nav_Result:
           //     Toast.makeText(this, "Result", Toast.LENGTH_SHORT).show();
              /*  Intent i4= new Intent(Navigation.this,Result.class);
                i4.putExtra("key",4);
                startActivity(i4);
                *///break;
            case R.id.nav_About:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                Intent i5= new Intent(profilee.this,About.class);
                i5.putExtra("key",5);
                startActivity(i5);
                break;
            case R.id.nav_Settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                //Intent i7= new Intent(MainActivity.this,.class);
                // i7.putExtra("key",7);
                // startActivity(i7);
                break;
            case R.id.nav_Logout:
                Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
              /*  Intent i= new Intent(Navigation.this,SelectSemester.class);
                i.putExtra("key",6);
                startActivity(i);
                */break;
            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
            //copy this now.
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

