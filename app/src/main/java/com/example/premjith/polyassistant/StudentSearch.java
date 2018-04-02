package com.example.premjith.polyassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentSearch extends AppCompatActivity {
String reg;
    FirebaseDatabase database;
    DatabaseReference myRef;
EditText tRGnum;
ProgressBar pbSearch;
View pbS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        tRGnum= findViewById(R.id.txt_rg_number);
        pbSearch=findViewById(R.id.pBar3_search);
        pbS=pbSearch;
        pbS.setVisibility(View.GONE);


        findViewById(R.id.search_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbS.setVisibility(View.VISIBLE);
                reg=tRGnum.getText().toString();
                database=FirebaseDatabase.getInstance();
                myRef=database.getReference("tekerala").child(""+reg).child("permission");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            if (dataSnapshot.getValue().toString().equals("1")) {

                                Intent n = new Intent(getApplicationContext(), StudentCGPA.class);
                                n.putExtra("MY_REG", reg);
                                n.putExtra("MY_KEY", 1);
                                startActivity(n);

                            }

                        } else {
                            pbS.setVisibility(View.GONE);
                            Toast.makeText(StudentSearch.this, "Grades are under verifying process", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();

        pbS.setVisibility(View.GONE);
    }


}
