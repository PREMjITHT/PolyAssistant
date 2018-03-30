package com.example.premjith.polyassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentSearch extends AppCompatActivity {
String reg;
EditText tRGnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        tRGnum= findViewById(R.id.txt_rg_number);
        findViewById(R.id.search_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg=tRGnum.getText().toString();
                Toast.makeText(StudentSearch.this, "reg="+reg, Toast.LENGTH_SHORT).show();
                Intent n=new Intent(getApplicationContext(),StudentCGPA.class);
                n.putExtra("MY_REG",reg);
                startActivity(n);




            }
        });
    }
}
