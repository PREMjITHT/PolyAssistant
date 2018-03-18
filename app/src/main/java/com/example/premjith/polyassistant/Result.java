package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    float r=0;

    Button btnResultView,btnNextSem;
    TextView tvComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvComment=findViewById(R.id.tv_comment);
        btnNextSem = findViewById(R.id.btn_next);
        btnResultView=findViewById(R.id.btnResultView);


        r=getIntent().getExtras().getFloat("reslt");
        btnResultView.setText(""+r);
        if(r>9){
            tvComment.setText("Excellent...");
        }else if(r>8){
            tvComment.setText("Keep it up");
        }else if(r>7){
            tvComment.setText("Not bad");
        }else {
            tvComment.setText("Keep trying");
        }

        btnNextSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            finish();

                //number++;
                Intent no=new Intent(getApplicationContext(),Subjects.class);
                startActivity(no);

                //numberPicker.setValue(numberPicker.getValue() + 1);
                //finish();
                //startActivity(getIntent());

//
            }
        });

    }
}
