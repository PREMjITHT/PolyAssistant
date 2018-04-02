package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectSemester extends AppCompatActivity {

    int semID=1,branchID=1,CID;
    String classRoom;
    FirebaseAuth mAuth;
    String reg;
    FirebaseDatabase database;
    DatabaseReference myRef;
    NumberPicker numberPicker;
    TextView tvSem,tvSemNumber;
    Button btnToScroll2;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sem);
          tvSem = findViewById(R.id.tv_semester);
          tvSemNumber = findViewById(R.id.tv_sem_number);

            CID=getIntent().getExtras().getInt("MY_CID");
            reg=getIntent().getExtras().getString("MY_REG");
            branchID=getIntent().getExtras().getInt("MY_BRANCH");
            classRoom=getIntent().getExtras().getString("MY_CLASS");


         numberPicker = findViewById(R.id.np);
       // tv.setTextColor(Color.parseColor("#fff"));
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(6);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                semID = newVal;
                tvSemNumber.setText(""+semID);

            }
        });





            btnToScroll2 = findViewById(R.id.btn_select_semester);
            btnToScroll2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            Intent in=new Intent(getApplicationContext(),Subjects.class);
            in.putExtra("MY_CID",CID);
            in.putExtra("MY_REG",reg);
            in.putExtra("MY_CLASS",classRoom);
            in.putExtra("MY_SEM",semID);
            in.putExtra("MY_BRANCH",branchID);
            startActivity(in);


                }
            });






    }




}
