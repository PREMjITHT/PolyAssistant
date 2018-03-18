package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class SelectSemester extends AppCompatActivity {

    int semID=1,branchID=1;
    NumberPicker numberPicker;
    TextView tvSem,tvSemNumber;
    Button btnToScroll2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sem);


          tvSem = findViewById(R.id.tv_semester);
          tvSemNumber = findViewById(R.id.tv_sem_number);
          branchID= getIntent().getExtras().getInt("key");

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
                    Intent i = new Intent(SelectSemester.this, Subjects.class);
                    i.putExtra("MY_SEM", semID);
                    i.putExtra("MY_BRANCH", branchID);

                    startActivity(i);


                }
            });






    }

}
