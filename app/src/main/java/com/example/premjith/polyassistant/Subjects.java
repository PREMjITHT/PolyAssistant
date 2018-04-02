package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.CertPathValidatorException;

public class Subjects extends AppCompatActivity {

    int supp=0,CID=0;
    FirebaseAuth mAuth;
    float cgpa=0,cgpaSum=0,sum=0,aggr=0;
            int cgpaCount=1;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int SemNumber,BranchNumber,nCount=0,collegeID;
    Button btnNextsem,btnResult;
    Spinner spinnerFun;
    String g[]={"S","A","B","C","D","E","F"},pNumber,reg,classRoom;
    TextView tvSlot1,tvSlot2,tvSlot3,tvSlot4,tvSlot5,tvSlot6,tvSlot7,tvSlot8,tvSlot9,tvSlot10;
    Spinner SpinnerSlot1,SpinnerSlot2,SpinnerSlot3,SpinnerSlot4,SpinnerSlot5,SpinnerSlot6,SpinnerSlot7,SpinnerSlot8,SpinnerSlot9,SpinnerSlot10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        //reference..........................................................
        btnResult = findViewById(R.id.btn_Result);
        btnNextsem = findViewById(R.id.btn_Next_Sem_Result);
        SpinnerSlot1 = findViewById(R.id.spinner_slot1);
        SpinnerSlot2 = findViewById(R.id.spinner_slot2);
        SpinnerSlot3 = findViewById(R.id.spinner_slot3);
        SpinnerSlot4 = findViewById(R.id.spinner_slot4);
        SpinnerSlot5 = findViewById(R.id.spinner_slot5);
        SpinnerSlot6 = findViewById(R.id.spinner_slot6);
        SpinnerSlot7 = findViewById(R.id.spinner_slot7);
        SpinnerSlot8 = findViewById(R.id.spinner_slot8);
        SpinnerSlot9 = findViewById(R.id.spinner_slot9);
        SpinnerSlot10 = findViewById(R.id.spinner_slot10);

        tvSlot1 = findViewById(R.id.tv_slot1);
        tvSlot2 = findViewById(R.id.tv_slot2);
        tvSlot3 = findViewById(R.id.tv_slot3);
        tvSlot4 = findViewById(R.id.tv_slot4);
        tvSlot5 = findViewById(R.id.tv_slot5);
        tvSlot6 = findViewById(R.id.tv_slot6);
        tvSlot7 = findViewById(R.id.tv_slot7);
        tvSlot8 = findViewById(R.id.tv_slot8);
        tvSlot9 = findViewById(R.id.tv_slot9);
        tvSlot10 = findViewById(R.id.tv_slot10);


        //...............................................................

        ArrayAdapter<String> adptr1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot1.setAdapter(adptr1);
        ArrayAdapter<String> adptr2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot2.setAdapter(adptr2);
        ArrayAdapter<String> adptr3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot3.setAdapter(adptr3);
        ArrayAdapter<String> adptr4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot4.setAdapter(adptr4);
        ArrayAdapter<String> adptr5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot5.setAdapter(adptr5);
        ArrayAdapter<String> adptr6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot6.setAdapter(adptr6);
        ArrayAdapter<String> adptr7 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot7.setAdapter(adptr7);
        ArrayAdapter<String> adptr8 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot8.setAdapter(adptr8);
        ArrayAdapter<String> adptr9 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot9.setAdapter(adptr9);
        ArrayAdapter<String> adptr10 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, g);
        SpinnerSlot10.setAdapter(adptr10);












        //Getting semester id from select semester activity
        SemNumber = getIntent().getExtras().getInt("MY_SEM");
        classRoom=getIntent().getExtras().getString("MY_CLASS");
        reg=getIntent().getExtras().getString("MY_REG");


        BranchNumber = getIntent().getExtras().getInt("MY_BRANCH");
        CID=getIntent().getExtras().getInt("MY_CID");

        if (BranchNumber==1){
            if (SemNumber==1){
                nCount=6;
            }else if (SemNumber==2){
                nCount=10;
            }else if (SemNumber==3||SemNumber==4||SemNumber==5){
                nCount=8;
            }
            else if (SemNumber==6){
                nCount=7;
            }
        }/*else if (BranchNumber==2){
            if (SemNumber==1){
                nCount=6;
            }else if (SemNumber==2){
                nCount=10;
            }else if (SemNumber==3||SemNumber==4||SemNumber==5||SemNumber==6){
                nCount=8;
            }

        }*/
        reStoreData(BranchNumber, SemNumber,nCount);


        setSubject(SemNumber, BranchNumber);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null) {
            pNumber = mAuth.getCurrentUser().getPhoneNumber();


        }


            /* reStoreData(BranchNumber,SemNumber,SpinnerSlot2);
        reStoreData(BranchNumber,SemNumber,SpinnerSlot3);
        reStoreData(BranchNumber,SemNumber,SpinnerSlot4);
        reStoreData(BranchNumber,SemNumber,SpinnerSlot5);
        reStoreData(BranchNumber,SemNumber,SpinnerSlot6);
        reStoreData(BranchNumber,SemNumber,SpinnerSlot7);
        reStoreData(BranchNumber,SemNumber,SpinnerSlot8);*/

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
                a1 = sswitch(SpinnerSlot1,SemNumber);
                a2 = sswitch(SpinnerSlot2,SemNumber);
                a3 = sswitch(SpinnerSlot3,SemNumber);
                a4 = sswitch(SpinnerSlot4,SemNumber);
                a5 = sswitch(SpinnerSlot5,SemNumber);
                a6 = sswitch(SpinnerSlot6,SemNumber);
                a7 = sswitch(SpinnerSlot7,SemNumber);
                a8 = sswitch(SpinnerSlot8,SemNumber);
                a9 = sswitch(SpinnerSlot9,SemNumber);
                a10 = sswitch(SpinnerSlot10,SemNumber);


                // tvSemesterInSum.setText(""+number);
                if (BranchNumber == 1) {
                    //CALCULATING CGPA OF COMPUTER
                    if (SemNumber == 1) {

                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 2) + (a6 * 4);
                        cgpa = sum / 21;



                    } else if (SemNumber == 2) {

                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 4) + (a6 * 5) + (a7 * 3) + (a8 * 3) + (a9 * 2) + (a10 * 2);
                        cgpa = sum / 34;



                    } else if (SemNumber == 3) {


                        sum += (a1 * 4) + (a2 * 5) + (a3 * 4) + (a4 * 4) + (a5 * 3) + (a6 * 3) + (a7 * 3) + (a8 * 3);
                        cgpa = sum / 29;


                    } else if (SemNumber == 4) {

                        sum += (a1 * 4) + (a2 * 4) + (a3 * 5) + (a4 * 4) + (a5 * 3) + (a6 * 3) + (a7 * 3) + (a8 * 5);
                        cgpa = sum / 31;



                    } else if (SemNumber == 5) {
                        sum += (a1 * 4) + (a2 * 4) + (a3 * 4) + (a4 * 4) + (a5 * 2) + (a6 * 2) + (a7 * 2) + (a8 * 3);
                        cgpa = sum / 25;


                    } else if (SemNumber == 6) {
                        sum += (a1 * 5) + (a2 * 4) + (a3 * 5) + (a4 * 5) + (a5 * 3) + (a6 * 3) + (a7 * 10);
                        cgpa = sum / 35;

                    }
                } else if (BranchNumber == 2) {
                      //calculate cgpa of
                    if (SemNumber==1){
                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 2) + (a6 * 4);
                        cgpa = sum / 21;

                    }else if (SemNumber==2){
                        sum += (a1 * 3) + (a2 * 6) + (a3 * 3) + (a4 * 3) + (a5 * 4) + (a6 * 5) + (a7 * 3)+(a8*3)+(a9*2)+(a1*2);
                        cgpa = sum / 34;

                    }else if (SemNumber==3){
                        sum += (a1 * 6) + (a2 * 6) + (a3 * 6) + (a4 * 3) + (a5 * 3) + (a6 * 2) + (a7 * 2)+(a8*2);
                        cgpa = sum / 30;

                    }else if (SemNumber==4){
                        sum += (a1 * 5) + (a2 * 5) + (a3 * 5) + (a4 * 4) + (a5 * 2) + (a6 * 2) + (a7 * 3)+(a8*5);
                        cgpa = sum / 31;

                    }else if (SemNumber==5){
                        sum += (a1 * 4) + (a2 * 5) + (a3 * 2) + (a4 * 3) + (a5 * 4) + (a6 * 2) + (a7 * 3)+(a8*2);
                        cgpa = sum / 25;

                    }else if (SemNumber==6){
                        sum += (a1 * 4) + (a2 * 4) + (a3 * 5) + (a4 * 4) + (a5 * 3) + (a6 * 2) + (a7 * 2)+(a8*10);
                        cgpa = sum / 34;
                    }


                }


                storeData(BranchNumber,SemNumber);
                database=FirebaseDatabase.getInstance();
                myRef=database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("cgpa");
                myRef.setValue(""+cgpa);
                myRef=database.getReference("tekerala").child(""+reg).child("cgpa").child(""+SemNumber);
                myRef.setValue(""+cgpa);

                cgpaSum+=cgpa;
                if (cgpaCount!=0) {
                     aggr = cgpaSum / (cgpaCount );
                }else {
                    aggr=cgpaSum;
                }

                Intent n15 = new Intent(getApplicationContext(), Result.class);
                n15.putExtra("reslt", cgpa);
                n15.putExtra("phon",pNumber);
                n15.putExtra("S", SemNumber);
                n15.putExtra("B",BranchNumber);
                n15.putExtra("A",aggr);
                startActivity(n15);


                // number++;
            }
        });

        btnNextsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (SemNumber < 6) {

                    View bb1 = findViewById(R.id.lay_slot10);
                    bb1.setVisibility(View.VISIBLE);
                    View bb2 = findViewById(R.id.lay_slot9);
                    bb2.setVisibility(View.VISIBLE);
                    View bb3 = findViewById(R.id.lay_slot8);
                    bb3.setVisibility(View.VISIBLE);
                    View bb4 = findViewById(R.id.lay_slot7);
                    bb4.setVisibility(View.VISIBLE);
                    SpinnerSlot1.setSelection(0);
                    SpinnerSlot2.setSelection(0);
                    SpinnerSlot3.setSelection(0);
                    SpinnerSlot4.setSelection(0);
                    SpinnerSlot5.setSelection(0);
                    SpinnerSlot6.setSelection(0);
                    SpinnerSlot7.setSelection(0);
                    SpinnerSlot8.setSelection(0);
                    SpinnerSlot9.setSelection(0);
                    SpinnerSlot10.setSelection(0);
                    SemNumber++;
                    setSubject(SemNumber, BranchNumber);

                        reStoreData(BranchNumber, SemNumber,nCount);

                     }
                else {
                    Toast.makeText(Subjects.this, "Course completed...!", Toast.LENGTH_SHORT).show();
                }

                cgpaCount++;

                //dump code
                //finish();
                //startActivity(getIntent());


            }
        });





    }


//method for storing daata

    public void storeData(int b,int s){
        int n=0;
        supp=0;
        int  a1=SpinnerSlot1.getSelectedItemPosition();
        int a2=SpinnerSlot2.getSelectedItemPosition();
        int a3=SpinnerSlot3.getSelectedItemPosition();
        int a4=SpinnerSlot4.getSelectedItemPosition();
        int a5=SpinnerSlot5.getSelectedItemPosition();
        int a6=SpinnerSlot6.getSelectedItemPosition();
        int a7=SpinnerSlot7.getSelectedItemPosition();
        int a8=SpinnerSlot8.getSelectedItemPosition();
        int a9=SpinnerSlot9.getSelectedItemPosition();
        int a10=SpinnerSlot10.getSelectedItemPosition();
        if (a1==6)
            supp++;
         if (a2==6)
            supp++;
         if (a3==6)
            supp++;
         if(a4==6)
            supp++;
         if(a5==6)
          supp++;
         if(a6==6)
            supp++;
         if(a7==6)
            supp++;
         if(a8==6)
            supp++;
         if(a9==6)
            supp++;
         if(a10==6)
        supp++;


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("supply");
        myRef.setValue(""+supp);

        database=FirebaseDatabase.getInstance();

        if(b==1){
            if (s==1){

                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
            }else if (s==2){
                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
                saveSlote(b,s,7,a7);
                saveSlote(b,s,8,a8);
                saveSlote(b,s,9,a9);
                saveSlote(b,s,10,a10);

            }else if (s==3||s==4||s==5){
                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
                saveSlote(b,s,7,a7);
                saveSlote(b,s,8,a8);

             }else if (s==6){
                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
                saveSlote(b,s,7,a7);

            }

        }else if (b==2){
            if (s==1){

                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
            }else if (s==2){
                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
                saveSlote(b,s,7,a7);
                saveSlote(b,s,8,a8);
                saveSlote(b,s,9,a9);
                saveSlote(b,s,10,a10);

            }else if (s==3||s==4||s==5||s==6){
                saveSlote(b,s,1,a1);
                saveSlote(b,s,2,a2);
                saveSlote(b,s,3,a3);
                saveSlote(b,s,4,a4);
                saveSlote(b,s,5,a5);
                saveSlote(b,s,6,a6);
                saveSlote(b,s,7,a7);
                saveSlote(b,s,8,a8);

            }
        }
        else {
            Toast.makeText(this, "Not entered", Toast.LENGTH_SHORT).show();
        }




    }
//simple method for storing the grades
    public void saveSlote(int b,int s,int n,int a){
        myRef=database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+n);
               myRef.setValue(""+a);
    }

    public void reStoreData(int b, int s,int c) {


        if (c==6){
            Toast.makeText(this, "collegeIdinner func="+CID, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "studentid="+reg, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "branch="+BranchNumber, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "semester="+SemNumber, Toast.LENGTH_SHORT).show();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child("" +SemNumber).child("slote").child(""+1);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                SpinnerSlot1.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    Toast.makeText(Subjects.this, "restore value="+Integer.parseInt(dataSnapshot.getValue().toString()), Toast.LENGTH_SHORT).show();
            }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child("" +SemNumber).child("slote").child(""+2);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot2.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                        Toast.makeText(Subjects.this, "restore value="+Integer.parseInt(dataSnapshot.getValue().toString()), Toast.LENGTH_SHORT).show();
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child("" +SemNumber).child("slote").child(""+3);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot3.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                        Toast.makeText(Subjects.this, "restore value="+Integer.parseInt(dataSnapshot.getValue().toString()), Toast.LENGTH_SHORT).show();
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child("" +SemNumber).child("slote").child(""+4);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot4.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                        Toast.makeText(Subjects.this, "restore value="+Integer.parseInt(dataSnapshot.getValue().toString()), Toast.LENGTH_SHORT).show();
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child("" +SemNumber).child("slote").child(""+5);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot5.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                        Toast.makeText(Subjects.this, "restore value="+Integer.parseInt(dataSnapshot.getValue().toString()), Toast.LENGTH_SHORT).show();
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child("" +SemNumber).child("slote").child(""+6);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot6.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                        Toast.makeText(Subjects.this, "restore value="+Integer.parseInt(dataSnapshot.getValue().toString()), Toast.LENGTH_SHORT).show();
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }else if (c==10){

            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+1);
            //readData(spin);



            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot1.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+2);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot2.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+3);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot3.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+4);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot4.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+5);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot5.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+6);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot6.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+7);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot7.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+8);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot8.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+9);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot9.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+10);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot10.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });






            }else if (c==8) {

            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+1);
            //readData(spin);



            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot1.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+2);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot2.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+3);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot3.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+4);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot4.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+5);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot5.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+6);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot6.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+7);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot7.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+8);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot8.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else if (c==7){
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+1);
            //readData(spin);



            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot1.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+2);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot2.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+3);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot3.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+4);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot4.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+5);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot5.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+6);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot6.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            myRef = database.getReference("collegeID").child(""+CID).child(""+classRoom).child("student").child(""+reg).child("branch").child(""+BranchNumber).child("semester").child(""+SemNumber).child("slote").child(""+7);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        SpinnerSlot7.setSelection(Integer.parseInt(dataSnapshot.getValue().toString()));
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



    }



    //spinner data check..................................................

    public int sswitch(Spinner v,int sem){

        int z=0;
        String gradeWeb=v.getSelectedItem().toString();

        switch (gradeWeb){
            case "S":z=10;break;
            case "A":z= 9;break;
            case "B":z=8;break;
            case "C":z=7;break;
            case "D":z=6;break;
            case "E":z=5;break;
            case "F":return 0;



        }

    return z;

    }

    //save data..................................



//set subjects for layouts...........................................
    public void setSubject(int SID,int BID){


        if (BID == 1) {
            if (SID == 1) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                View b3 = findViewById(R.id.lay_slot8);
                b3.setVisibility(View.GONE);
                View b4 = findViewById(R.id.lay_slot7);
                b4.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_english_i));
                tvSlot2.setText(getString(R.string.sub_mathematics_i));
                tvSlot3.setText(getString(R.string.sub_physics_i));
                tvSlot4.setText(getString(R.string.sub_chemistry_i));
                tvSlot5.setText(getString(R.string.sub_hpe));
                tvSlot6.setText(getString(R.string.sub_computing_fund));
            } else if (SID == 2) {
                tvSlot1.setText(getString(R.string.sub_english_ii));
                tvSlot2.setText(getString(R.string.sub_maths_ii));
                tvSlot3.setText(getString(R.string.sub_physics_ii));
                tvSlot4.setText(getString(R.string.sub_chemistry_ii));
                tvSlot5.setText(getString(R.string.sub_programming_in_c));
                tvSlot6.setText(getString(R.string.sub_engineering_graphics));
                tvSlot7.setText(getString(R.string.sub_Workshop_practice));
                tvSlot8.setText(getString(R.string.sub_sub_science_lab));
                tvSlot9.setText(getString(R.string.sub_programmin_in_c_lab));
                tvSlot10.setText(getString(R.string.sub_life_skill));
            } else if (SID == 3) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_dcp));
                tvSlot2.setText(getString(R.string.sub__oop_C));
                tvSlot3.setText(getString(R.string.sub_ca));
                tvSlot4.setText(getString(R.string.sub_dbms));
                tvSlot5.setText(getString(R.string.sub_evs));
                tvSlot6.setText(getString(R.string.sub_dcp_lab));
                tvSlot7.setText(getString(R.string.sub_oop_lab));
                tvSlot8.setText(getText(R.string.sub_dbms_lab));
            } else if (SID == 4) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_data_communication));
                tvSlot2.setText(getString(R.string.sub_operating_system));
                tvSlot3.setText(getString(R.string.sub_data_structure));
                tvSlot4.setText(getString(R.string.sub_Computer_System_Hardware));
                tvSlot5.setText(getString(R.string.sub_system_admin_lab));
                tvSlot6.setText(getString(R.string.sub_data_structure_lab));
                tvSlot7.setText(getString(R.string.sub_csh_lab));
                tvSlot8.setText(getText(R.string.sub_app_dev_lab));
            } else if (SID == 5) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_softare_eng));
                tvSlot2.setText(getString(R.string.sub_web_programmin));
                tvSlot3.setText(getString(R.string.sub_microprocessor));
                tvSlot4.setText(getString(R.string.sub_elective));
                tvSlot5.setText(getString(R.string.sub_web_programmin));
                tvSlot6.setText(getString(R.string.sub_mp_lab));
                tvSlot7.setText(getString(R.string.sub_industrial_visit));
                tvSlot8.setText(getText(R.string.sub_computer_networks));

            } else if (SID == 6) {
                tvSlot1.setText(getString(R.string.sub_mc));
                tvSlot2.setText(getString(R.string.sub_cn));
                tvSlot3.setText(getString(R.string.sub_smart_device_programmin));
                tvSlot4.setText(getString(R.string.sub_elective));
                tvSlot5.setText(getString(R.string.sub_mc_lab));
                tvSlot6.setText(getString(R.string.sub_sdp_lab));
                tvSlot7.setText(getString(R.string.sub_project_seminar));
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                View b3 = findViewById(R.id.lay_slot8);
                b3.setVisibility(View.GONE);
            } else {
                Toast.makeText(this, "Course completed..", Toast.LENGTH_SHORT).show();
            }
        }else if (BID==2){
            Toast.makeText(this, "civil", Toast.LENGTH_SHORT).show();

            if (SID == 1) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                View b3 = findViewById(R.id.lay_slot8);
                b3.setVisibility(View.GONE);
                View b4 = findViewById(R.id.lay_slot7);
                b4.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_english_i));
                tvSlot2.setText(getString(R.string.sub_mathematics_i));
                tvSlot3.setText(getString(R.string.sub_physics_i));
                tvSlot4.setText(getString(R.string.sub_chemistry_i));
                tvSlot5.setText(getString(R.string.sub_hpe));
                tvSlot6.setText(getString(R.string.sub_computing_fund));
            } else if (SID == 2) {
                tvSlot1.setText(getString(R.string.sub_english_ii));
                tvSlot2.setText(getString(R.string.sub_maths_ii));
                tvSlot3.setText(getString(R.string.sub_physics_ii));
                tvSlot4.setText(getString(R.string.sub_chemistry_ii));
                tvSlot5.setText(getString(R.string.sub_surveying_i));
                tvSlot6.setText(getString(R.string.sub_engineering_graphics));
                tvSlot7.setText(getString(R.string.sub_Workshop_practice));
                tvSlot8.setText(getString(R.string.sub_science_lab_ii));
                tvSlot9.setText(getString(R.string.sub_survey_practical_i));
                tvSlot10.setText(getString(R.string.sub_life_skill));
            } else if (SID == 3) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_theory_structures_i));
                tvSlot2.setText(getString(R.string.sub_surveying_ii));
                tvSlot3.setText(getString(R.string.sub_construction_materials_eng));
                tvSlot4.setText(getString(R.string.sub_evs));
                tvSlot5.setText(getString(R.string.sub_building_planning_and_drawing));
                tvSlot6.setText(getString(R.string.sub_survey_practical_ii));
                tvSlot7.setText(getString(R.string.sub_construction_lab));
                tvSlot8.setText(getText(R.string.sub_concrete_lab));
            } else if (SID == 4) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_theory_structures_ii));
                tvSlot2.setText(getString(R.string.sub_quantity_surveying_i));
                tvSlot3.setText(getString(R.string.sub_hydraulics));
                tvSlot4.setText(getString(R.string.sub_irrigation_engineering));
                tvSlot5.setText(getString(R.string.sub_survey_practical_iii));
                tvSlot6.setText(getString(R.string.sub_hydraulics_lab));
                tvSlot7.setText(getString(R.string.sub_computer_aided_drafting_lab));
                tvSlot8.setText(getText(R.string.sub_mini_project));
            } else if (SID == 5) {
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);
                tvSlot1.setText(getString(R.string.sub_construction_management_and_safty_engineering));
                tvSlot2.setText(getString(R.string.sub_structural_design_i));
                tvSlot3.setText(getString(R.string.sub_geotechnical_engineering));
                tvSlot4.setText(getString(R.string.sub_quantity_surveying_ii));
                tvSlot5.setText(getString(R.string.sub_elective));
                tvSlot6.setText(getString(R.string.sub_geotechnical_engineering_lab));
                tvSlot7.setText(getString(R.string.sub_computer_appication_lab));
                tvSlot8.setText(getText(R.string.sub_industrial_visit));

            } else if (SID == 6) {
                tvSlot1.setText(getString(R.string.sub_structural_design_ii));
                tvSlot2.setText(getString(R.string.sub_environmental_engineering));
                tvSlot3.setText(getString(R.string.sub_transportation_engineering));
                tvSlot4.setText(getString(R.string.sub_elective));
                tvSlot5.setText(getString(R.string.sub_structural_and_irrigation_engineering_drawing));
                tvSlot6.setText(getString(R.string.sub_material_lesting_lab));
                tvSlot7.setText(getString(R.string.sub_environmental_engineering_lab));
                tvSlot8.setText(getString(R.string.sub_project_seminar));
                View b1 = findViewById(R.id.lay_slot10);
                b1.setVisibility(View.GONE);
                View b2 = findViewById(R.id.lay_slot9);
                b2.setVisibility(View.GONE);

            }


        }

        }






    @Override
    public void onResume(){
        super.onResume();
      // setSubject(SemNumber,BranchNumber);
        sum=0;
        cgpa=0;

    }




}

