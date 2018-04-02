package com.example.premjith.polyassistant;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Result extends AppCompatActivity {
    float r=0,rr=0;
    int count=1;
    String p,s1,s2;
    int flag=1,b,s;
    Button btnResultView,btnNextSem,btnSupply,btnAggragate;
    TextView tvComment;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvComment=findViewById(R.id.tv_comment);
        btnNextSem = findViewById(R.id.btn_next);
        btnResultView=findViewById(R.id.btnResultView);
        btnSupply=findViewById(R.id.btn_supply);
        btnAggragate=findViewById(R.id.btn_aggragate_cgpa);
        rr=getIntent().getExtras().getFloat("A");
        r=getIntent().getExtras().getFloat("reslt");
        p=getIntent().getExtras().getString("phon");
        b=getIntent().getExtras().getInt("B");
        s=getIntent().getExtras().getInt("S");
        btnAggragate.setText(""+rr);
        String s = String.format("%.2f", r);
        btnResultView.setText(s);
        if(r>9){
            tvComment.setText("Excellent...");
        }else if(r>8){
            tvComment.setText("Keep it up");
        }else if(r>7){
            tvComment.setText("Not bad");
        }else {
            tvComment.setText("Keep trying");
        }



            database= FirebaseDatabase.getInstance();
        myRef=database.getReference().child(""+p).child("branch").child(""+b).child("total");
        myRef.setValue(""+rr);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String sss = dataSnapshot.getValue().toString();
                    btnAggragate.setText(sss);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
           /* myRef=database.getReference(""+p).child("branch").child(""+b).child("semester").child(""+s).child("supply");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override

                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()){

                        String sss=dataSnapshot.getValue().toString();
                        btnSupply.setText(sss);


                }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
*/    /*  database=FirebaseDatabase.getInstance();
        myRef=database.getReference(""+p).child("branch").child(""+b).child("semester").child(""+s).child("cgpa");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    String ss=dataSnapshot.getValue().toString();
                    btnResultView.setText(ss);


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/










        btnNextSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            finish();

                //number++;
                //numberPicker.setValue(numberPicker.getValue() + 1);
                //finish();
                //startActivity(getIntent());

//
            }
        });

    }
}
