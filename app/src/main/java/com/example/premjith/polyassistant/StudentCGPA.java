package com.example.premjith.polyassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentCGPA extends AppCompatActivity {
String r,name;
FirebaseDatabase database;
DatabaseReference mref;
TextView tvName,tvReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_cgp);

        tvName=findViewById(R.id.tv_stud_name);
        tvReg=findViewById(R.id.tv_stud_reg);
        r=getIntent().getExtras().getString("MY_REG");
        tvReg.setText(r);
        database=FirebaseDatabase.getInstance();
        mref=database.getReference("tekerala").child(""+r).child("name");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                   name=dataSnapshot.getValue().toString();
                    Toast.makeText(StudentCGPA.this, "name="+name, Toast.LENGTH_SHORT).show();
                    tvName.setText(""+name);
                }else {

                    tvName.setText("NO STUDENT");
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
}
