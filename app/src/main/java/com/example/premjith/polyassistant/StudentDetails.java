package com.example.premjith.polyassistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class StudentDetails extends AppCompatActivity implements
        View.OnClickListener {
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    Button btnDatePicker,btnStudNext;
    EditText txtDate,txtRegNum;
    int mYear, mMonth, mDay,b,s,x;
    String pNumber,reg;
    int collegeID,CID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        btnStudNext=findViewById(R.id.btn_stud_next);
        btnDatePicker=findViewById(R.id.btn_date);
        txtDate=findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);
        txtRegNum=findViewById(R.id.txt_student_reg_num);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth!=null) {
            pNumber = mAuth.getCurrentUser().getPhoneNumber();

        }
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("" + pNumber).child("collegeID");
        Toast.makeText(this, "phon test"+pNumber, Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(StudentDetails.this, "00000000000000000000000000000000", Toast.LENGTH_SHORT).show();
                    CID=Integer.parseInt(dataSnapshot.getValue().toString());
                    Toast.makeText(StudentDetails.this, "CID="+CID, Toast.LENGTH_SHORT).show();
                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        b=getIntent().getExtras().getInt("MY_BRANCH");
        s=getIntent().getExtras().getInt("MY_SEM");









        btnStudNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        Intent in = new Intent(getApplicationContext(), Subjects.class);
        in.putExtra("MY_SEM", s);
        in.putExtra("MY_BRANCH", b);
        in.putExtra("MY_REG", reg);
       in.putExtra("MY_CID", CID);
       startActivity(in);
       Toast.makeText(StudentDetails.this, "cid moooooosaa=" + CID, Toast.LENGTH_SHORT).show();




        }


             });





        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("" + pNumber).child("collegeID");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    collegeID=Integer.parseInt(dataSnapshot.getValue().toString());
                    Toast.makeText(StudentDetails.this, "id="+collegeID, Toast.LENGTH_SHORT).show();
                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {


            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            reg=txtRegNum.getText().toString();
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("collegeID").child(""+collegeID).child("student").child(""+reg).child("DOB");
                            myRef.setValue(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);



                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }



}

