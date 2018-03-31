package com.example.premjith.polyassistant;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateClass extends Activity {
FloatingActionButton fab;
    final Context context = this;
ListView lvClass;
String name,tut;
EditText txtNamePro,txtTutor;
ArrayList<String> classList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
    lvClass=findViewById(R.id.lv_class);
    fab=findViewById(R.id.fabbtn);
    txtNamePro=findViewById(R.id.txt_name_prompt);
    txtTutor=findViewById(R.id.txt_tutor_prompt);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classList);
        lvClass.setAdapter(arrayAdapter);




    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.prompts, null);

            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = promptsView
                    .findViewById(R.id.txt_name_prompt);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Create class",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    //name=txtName.getText().toString();
                                    //tut=txtTutor.getText().toString();
                                    classList.add(txtNamePro.getText().toString());
                                    arrayAdapter.notifyDataSetChanged();
                                    Toast.makeText(CreateClass.this, "create", Toast.LENGTH_SHORT).show();
                                   // result.setText(userInput.getText());
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            android.app.AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();



        }
    });

    }
}
