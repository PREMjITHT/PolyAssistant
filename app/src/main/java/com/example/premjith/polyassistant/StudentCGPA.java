package com.example.premjith.polyassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentCGPA extends AppCompatActivity {
String r,name;
Button s1,s2,s3,s4,s5,s6,aBig,btnAppr,btnIncor,btnSupply;
FirebaseDatabase database;
DatabaseReference myRef;
TextView tvName,tvReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_cgp);
        aBig=findViewById(R.id.btn_agg_big);
        btnAppr=findViewById(R.id.btn_approve);
        btnIncor=findViewById(R.id.btn_invalid);
        tvName=findViewById(R.id.tv_stud_name);
        s1=findViewById(R.id.btn_display_sem1);
        s2=findViewById(R.id.btn_display_sem2);
        s3=findViewById(R.id.btn_display_sem3);
        s4=findViewById(R.id.btn_display_sem4);
        s5=findViewById(R.id.btn_display_sem5);
        s6=findViewById(R.id.btn_display_sem6);
        tvReg=findViewById(R.id.tv_stud_reg);
        btnSupply=findViewById(R.id.btn_supply_cgpa);
        r=getIntent().getExtras().getString("MY_REG");
        int q=getIntent().getExtras().getInt("MY_KEY");
        tvReg.setText(r);

        if (q==1) {


            View b1 = btnAppr;
            b1.setVisibility(View.GONE);
            View b2 = btnIncor;
            b2.setVisibility(View.GONE);
        }
        else {
            View b1 = btnAppr;
            b1.setVisibility(View.VISIBLE);
            View b2 = btnIncor;
            b2.setVisibility(View.VISIBLE);
        }
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("tekerala").child(""+r).child("name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                   name=dataSnapshot.getValue().toString();

                    tvName.setText(""+name);
                }else {

                    tvName.setText("NO STUDENT");
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database=FirebaseDatabase.getInstance();

            myRef = database.getReference("tekerala").child("" + r).child("cgpa");//.child("" +i);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int count=0;
                    float sum=0,c=0,a1=0,a2=0,a3=0,a4=0,a5=0,a6=0;
                    if (dataSnapshot.child("1").exists()){
                         a1=Float.parseFloat(dataSnapshot.child("1").getValue().toString());
                    }
                    if (dataSnapshot.child("2").exists()){
                         a2=Float.parseFloat(dataSnapshot.child("2").getValue().toString());
                    }
                    if (dataSnapshot.child("3").exists()){
                         a3=Float.parseFloat(dataSnapshot.child("3").getValue().toString());
                    }

                    if (dataSnapshot.child("4").exists()){
                         a4=Float.parseFloat(dataSnapshot.child("4").getValue().toString());
                    }
                    if (dataSnapshot.child("5").exists()){
                         a5=Float.parseFloat(dataSnapshot.child("5").getValue().toString());
                    }
                    if (dataSnapshot.child("6").exists()){
                         a6=Float.parseFloat(dataSnapshot.child("6").getValue().toString());
                    }



                    if (a1!=0){
                        count+=1;
                        sum+=a1;
                    }
                    if (a2!=0){
                        count+=1;
                        sum+=a2;
                    }
                    if (a3!=0){
                        count+=1;
                        sum+=a3;
                    }
                    if (a4!=0){
                        count+=1;
                        sum+=a4;
                    }
                    if (a5!=0){
                        count+=1;
                        sum+=a5;
                    }if (a6!=0){
                        count+=1;
                        sum+=a6;
                    }

                    c=sum/count;

                    s1.setText(""+a1);
                    s2.setText(""+a2);
                    s3.setText(""+a3);
                    s4.setText(""+a4);
                    s5.setText(""+a5);
                    s6.setText(""+a6);
                    String s = String.format("%.2f", c);
                    aBig.setText(s);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        database=FirebaseDatabase.getInstance();

        myRef = database.getReference("tekerala").child("" +r).child("supply");//.child("" +i);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0;
                int sumSup=0,c=0,s1=0,s2=0,s3=0,s4=0,s5=0,s6=0;
                if (dataSnapshot.child("1").exists()){
                    s1=Integer.parseInt(dataSnapshot.child("1").getValue().toString());
                }
                if (dataSnapshot.child("2").exists()){
                    s2=Integer.parseInt(dataSnapshot.child("2").getValue().toString());
                }
                if (dataSnapshot.child("3").exists()){
                    s3=Integer.parseInt(dataSnapshot.child("3").getValue().toString());
                }
                if (dataSnapshot.child("4").exists()){
                    s4=Integer.parseInt(dataSnapshot.child("4").getValue().toString());
                }
                if (dataSnapshot.child("5").exists()){
                    s5=Integer.parseInt(dataSnapshot.child("5").getValue().toString());
                }
                if (dataSnapshot.child("6").exists()){
                    s6=Integer.parseInt(dataSnapshot.child("6").getValue().toString());
                }

                sumSup=s1+s2+s3+s4+s5+s6;

                btnSupply.setText(""+sumSup);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        btnAppr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database=FirebaseDatabase.getInstance();
                    myRef = database.getReference("tekerala").child("" + r).child("permission");
                    myRef.setValue(1);




                }
            });


        btnIncor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance();
                myRef = database.getReference("tekerala").child("" + r).child("permission");
                myRef.setValue(0);




            }
        });



    }
}
