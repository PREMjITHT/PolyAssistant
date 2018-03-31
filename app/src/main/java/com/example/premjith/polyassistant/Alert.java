package com.example.premjith.polyassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Alert extends Activity {
	ListView lv;
	final Context context = this;
	FloatingActionButton fab;
	ArrayList<String> dataList=new ArrayList<>();
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fab=findViewById(R.id.fab_butt);
		lv=findViewById(R.id.listv_prompt);
		// components from main
		final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
		lv.setAdapter(arrayAdapter);
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
										dataList.add(userInput.getText().toString());
										arrayAdapter.notifyDataSetChanged();
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