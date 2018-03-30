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

    int semID=1,branchID=1;
    FirebaseAuth mAuth;
    String p,user;
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
          branchID= getIntent().getExtras().getInt("key");
          x=getIntent().getExtras().getInt("ad");
        Toast.makeText(this, "semester X="+x, Toast.LENGTH_SHORT).show();

         numberPicker = findViewById(R.id.np);
       // tv.setTextColor(Color.parseColor("#fff"));
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(6);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                semID = newVal;
                //Toast.makeText(SelectSemester.this, "sem="+semID, Toast.LENGTH_SHORT).show();
                tvSemNumber.setText(""+semID);

            }
        });





            btnToScroll2 = findViewById(R.id.btn_select_semester);
            btnToScroll2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(SelectSemester.this, "sem="+semID, Toast.LENGTH_SHORT).show();


                    if (x==1){
                        Intent i = new Intent(SelectSemester.this, StudentDetails.class);
                        i.putExtra("MY_SEM", semID);
                        i.putExtra("MY_BRANCH", branchID);

                        startActivity(i);

                    } else if (x == 2) {
                        Intent i = new Intent(SelectSemester.this, StudentCGPA.class);
                        i.putExtra("MY_SEM", semID);
                        i.putExtra("MY_BRANCH", branchID);
                        startActivity(i);


                    }





                }
            });






    }




}
