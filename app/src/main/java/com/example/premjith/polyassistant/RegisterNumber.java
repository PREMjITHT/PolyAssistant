package com.example.premjith.polyassistant;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterNumber extends AppCompatActivity {
    int branchID=1,collegeID;
    ListView lvReg;
    String classRoom,pNumber;
    final Context context = this;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    ArrayList<String> RegList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number);

        branchID=getIntent().getExtras().getInt("MY_BRANCH");
        classRoom=getIntent().getExtras().getString("MY_CLASS");
        Toast.makeText(context, "class====="+classRoom, Toast.LENGTH_SHORT).show();

        lvReg=findViewById(R.id.listv_register);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,RegList);
        lvReg.setAdapter(arrayAdapter);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth!=null) {
            pNumber = mAuth.getCurrentUser().getPhoneNumber();

        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("register").child(""+classRoom);
       myRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               String value=dataSnapshot.getValue().toString();
               Toast.makeText(RegisterNumber.this, "value"+value, Toast.LENGTH_SHORT).show();
               RegList.add(value);
               arrayAdapter.notifyDataSetChanged();

               Toast.makeText(context, "register num activity="+collegeID, Toast.LENGTH_SHORT).show();

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





        lvReg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = lvReg.getItemAtPosition(position).toString().trim();
                Toast.makeText(context, "register list"+text, Toast.LENGTH_SHORT).show();



            }
        });
    }
}
