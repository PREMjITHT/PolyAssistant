package com.example.premjith.polyassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Alert extends Activity {
	ListView lv;
	int x,branchID;
	FirebaseDatabase database;
	DatabaseReference myRef;
	FirebaseAuth mAuth;
	String p;
	final Context context = this;
	FloatingActionButton fab;
	ArrayList<String> dataList=new ArrayList<>();
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fab=findViewById(R.id.fab_butt);
		lv=findViewById(R.id.listv_prompt);
		// components from main

		branchID= getIntent().getExtras().getInt("key");
		x=getIntent().getExtras().getInt("ad");
		final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
		lv.setAdapter(arrayAdapter);
		database = FirebaseDatabase.getInstance();
		myRef = database.getReference("classes");

		myRef.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
			String value=dataSnapshot.getValue(String.class);
			dataList.add(value);
			arrayAdapter.notifyDataSetChanged();


			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {

				String text = lv.getItemAtPosition(position).toString().trim();

				Intent in=new Intent(context,SelectSemester.class);
				if (x==1){
					Intent i = new Intent(context, StudentDetails.class);
					i.putExtra("MY_BRANCH", branchID);
					i.putExtra("MY_CLASS",text);

					startActivity(i);

				} else if (x == 2) {
					Intent i = new Intent(context,RegisterNumber.class);
					i.putExtra("MY_CLASS",text);
					i.putExtra("MY_BRANCH", branchID);
					startActivity(i);



				}
			}
		});


		fab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.prompts, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
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
										database = FirebaseDatabase.getInstance();
										myRef = database.getReference("classes").push();
										myRef.setValue(""+userInput.getText().toString());

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
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();



			}
		});





	}

}