package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;
    String u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mSignOutButton =  findViewById(R.id.sign_out_button);
        TextView fireBaseId =  findViewById(R.id.detail);




        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }
        mSignOutButton.setOnClickListener(this);





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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_out_button:

                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, PhoneAuthActivity.class));
                finish();
                break;
        }
    }







}
